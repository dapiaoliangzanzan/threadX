package com.threadx.metrics.server.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.constant.LockName;
import com.threadx.metrics.server.entity.ServerItem;
import com.threadx.metrics.server.lock.DistributedLockTemplate;
import com.threadx.metrics.server.mapper.ServerItemMapper;
import com.threadx.metrics.server.service.ServerItemService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * *************************************************<br/>
 * 服务详细的配置<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/5/8 16:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ServerItemServiceImpl extends ServiceImpl<ServerItemMapper, ServerItem> implements ServerItemService {

    private final DistributedLockTemplate distributedLockTemplate;

    public ServerItemServiceImpl(DistributedLockTemplate distributedLockTemplate) {
        this.distributedLockTemplate = distributedLockTemplate;
    }

    @Override
    public ServerItem findByName(String serverName) {
        if (StrUtil.isBlank(serverName)) {
            return null;
        }
        QueryWrapper<ServerItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_name", serverName);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public ServerItem findByNameOrCreate(String serverName) {
        distributedLockTemplate.tryLock(LockName.SERVER_CREATE_SELECT_LOCK);
        try {
            ServerItem serverItem = ((ServerItemServiceImpl) AopContext.currentProxy()).findByName(serverName);
            if (serverItem == null) {
                serverItem = new ServerItem();
                serverItem.init();
                serverItem.setServerName(serverName);
                ((ServerItemServiceImpl) AopContext.currentProxy()).save(serverItem);
            }
            return serverItem;
        } finally {
            distributedLockTemplate.unLock(LockName.SERVER_CREATE_SELECT_LOCK);
        }

    }
}
