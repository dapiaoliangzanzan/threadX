package com.threadx.metrics.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.threadx.metrics.server.conditions.InstanceItemFindConditions;
import com.threadx.metrics.server.constant.InstanceItemState;
import com.threadx.metrics.server.constant.LockName;
import com.threadx.metrics.server.constant.RedisCacheKey;
import com.threadx.metrics.server.entity.InstanceItem;
import com.threadx.metrics.server.entity.ServerItem;
import com.threadx.metrics.server.lock.DistributedLockTemplate;
import com.threadx.metrics.server.mapper.InstanceItemMapper;
import com.threadx.metrics.server.service.InstanceItemService;
import com.threadx.metrics.server.service.ServerItemService;
import com.threadx.metrics.server.vo.InstanceItemVo;
import com.threadx.metrics.server.vo.ThreadxPage;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * *************************************************<br/>
 * 实例信息实现类<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/5/8 16:31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InstanceItemServiceImpl extends ServiceImpl<InstanceItemMapper, InstanceItem> implements InstanceItemService {

    private final ServerItemService serverItemService;
    private final DistributedLockTemplate distributedLockTemplate;
    private final Cache<String, Long> taskCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();

    @Value("${threadx.instance.timeout}")
    private Long instanceTimout;

    public InstanceItemServiceImpl(ServerItemService serverItemService, DistributedLockTemplate distributedLockTemplate) {
        this.serverItemService = serverItemService;
        this.distributedLockTemplate = distributedLockTemplate;
    }

    @Override
    public ThreadxPage<InstanceItemVo> findByPage(InstanceItemFindConditions conditions) {
        if (conditions == null) {
            conditions = new InstanceItemFindConditions();
        }
        Integer pageSize = conditions.getPageSize();
        Integer pageNumber = conditions.getPageNumber();
        if (pageSize == null) {
            pageSize = 6;
        }
        if (pageNumber == null) {
            pageNumber = 1;
        }
        QueryWrapper<InstanceItem> queryWrapper = new QueryWrapper<>();
        //获取实例的名称
        String instanceItemName = conditions.getInstanceItemName();
        queryWrapper.like(StrUtil.isNotBlank(instanceItemName), "instance_name", instanceItemName);
        Page<InstanceItem> page = Page.of(pageNumber, pageSize);
        baseMapper.selectPage(page, queryWrapper);
        //获取分页查询到的数据
        List<InstanceItem> instanceItems = page.getRecords();
        ThreadxPage<InstanceItemVo> threadPage = new ThreadxPage<>();
        if (CollUtil.isNotEmpty(instanceItems)) {
            List<Long> serverIds = instanceItems.stream().map(InstanceItem::getServerId).collect(Collectors.toList());
            //根据服务的id查询服务的名称
            List<ServerItem> serverItems = serverItemService.findServerItemInId(serverIds);
            //将数据转换为Map
            Map<Long, String> serverItemMap = new HashMap<>();
            for (ServerItem serverItem : serverItems) {
                serverItemMap.put(serverItem.getId(), serverItem.getServerName());
            }

            List<InstanceItemVo> instanceItemVoList = instanceItems.stream().map(instanceItem -> {
                InstanceItemVo instanceItemVo = new InstanceItemVo();
                instanceItemVo.setState(InstanceItemState.ACTIVE);
                if (System.currentTimeMillis() - instanceItem.getActiveTime() > instanceTimout) {
                    instanceItemVo.setState(InstanceItemState.NOT_ACTIVE);
                }
                instanceItemVo.setId(instanceItem.getId());
                //获取服务名称
                Long serverId = instanceItem.getServerId();
                String serverName = serverItemMap.getOrDefault(serverId, "未知服务");
                instanceItemVo.setServerName(serverName);
                instanceItemVo.setInstanceName(instanceItem.getInstanceName());
                instanceItemVo.setCreateDate(DateUtil.format(new Date(instanceItem.getCreateTime()), "yyyy-MM-dd HH:mm:ss"));
                return instanceItemVo;
            }).collect(Collectors.toList());

            //设置数据
            threadPage.setData(instanceItemVoList);
            // 数据总量
            threadPage.setTotal(page.getTotal());
        }

        return threadPage;
    }

    @Override
    public InstanceItem findByInstanceNameAndServerName(String serverName, String instanceName) {
        if (StrUtil.isBlank(serverName) || StrUtil.isBlank(instanceName)) {
            return null;
        }

        ServerItem serverItem = serverItemService.findByName(serverName);
        if (serverItem == null) {
            return null;
        }

        QueryWrapper<InstanceItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_id", serverItem.getId());
        queryWrapper.eq("instance_name", instanceName);

        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public InstanceItem findByInstanceNameAndServerNameOrCreate(String serverName, String instanceName) {
        distributedLockTemplate.lock(LockName.INSTANCE_CREATE_SELECT_LOCK);
        try {
            if (StrUtil.isBlank(serverName) || StrUtil.isBlank(instanceName)) {
                return null;
            }
            InstanceItem instanceItem = ((InstanceItemServiceImpl) AopContext.currentProxy()).findByInstanceNameAndServerName(serverName, serverName);
            if (instanceItem == null) {
                ServerItem serverItem = serverItemService.findByNameOrCreate(serverName);
                instanceItem = new InstanceItem();
                instanceItem.init();
                instanceItem.setInstanceName(instanceName);
                instanceItem.setActiveTime(instanceItem.getCreateTime());
                instanceItem.setServerId(serverItem.getId());
                ((InstanceItemServiceImpl) AopContext.currentProxy()).save(instanceItem);
            }
            return instanceItem;
        } finally {
            distributedLockTemplate.unLock(LockName.INSTANCE_CREATE_SELECT_LOCK);
        }
    }

    @Override
    public Long findByInstanceNameAndServerNameOrCreateOnCache(String serverName, String instanceName) {
        //先从一级缓存中获取
        String cacheKey = String.format(RedisCacheKey.INSTANCE_ID_CACHE, serverName, instanceName);
        Long instanceId = taskCache.getIfPresent(cacheKey);
        if (instanceId == null) {
            //一级缓存没有从二级缓存中获取
            StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
            String instanceIdStr = redisTemplate.opsForValue().get(cacheKey);
            if (StrUtil.isNotBlank(instanceIdStr)) {
                instanceId = Long.parseLong(instanceIdStr);

            } else {
                //二级缓存没有就查数据库
                InstanceItem instanceItem = ((InstanceItemServiceImpl) AopContext.currentProxy()).findByInstanceNameAndServerNameOrCreate(serverName, instanceName);
                if (instanceItem == null) {
                    throw new RuntimeException("serverKey or instanceKey is null.");
                }
                instanceId = instanceItem.getId();
            }
            //写入或者更新到二级缓存
            redisTemplate.opsForValue().set(cacheKey, String.valueOf(instanceId), 1, TimeUnit.DAYS);
            //写入到一级缓存
            taskCache.put(cacheKey, instanceId);
        }
        return instanceId;
    }
}
