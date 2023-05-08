package com.threadx.metrics.server.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.threadx.metrics.server.constant.LockName;
import com.threadx.metrics.server.constant.RedisCacheKey;
import com.threadx.metrics.server.entity.InstanceItem;
import com.threadx.metrics.server.entity.ServerItem;
import com.threadx.metrics.server.lock.DistributedLockTemplate;
import com.threadx.metrics.server.mapper.InstanceItemMapper;
import com.threadx.metrics.server.service.InstanceItemService;
import com.threadx.metrics.server.service.ServerItemService;
import org.springframework.aop.framework.AopContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

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

    public InstanceItemServiceImpl(ServerItemService serverItemService, DistributedLockTemplate distributedLockTemplate) {
        this.serverItemService = serverItemService;
        this.distributedLockTemplate = distributedLockTemplate;
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
        //先从二级缓存中获取
        String cacheKey = String.format(RedisCacheKey.INSTANCE_ID_CACHE, serverName, instanceName);
        Long instanceId = taskCache.getIfPresent(cacheKey);
        if (instanceId == null) {
            //二级缓存没有从以及缓存中获取
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
                //写入到二级缓存
                redisTemplate.opsForValue().set(cacheKey, String.valueOf(instanceId), 1, TimeUnit.DAYS);
            }
            //写入到一级缓存
            taskCache.put(cacheKey, instanceId);
        }
        return instanceId;
    }
}
