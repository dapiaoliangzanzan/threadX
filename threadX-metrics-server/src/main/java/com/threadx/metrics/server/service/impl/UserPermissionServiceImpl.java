package com.threadx.metrics.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.entity.Permission;
import com.threadx.metrics.server.entity.UserPermission;
import com.threadx.metrics.server.mapper.PermissionMapper;
import com.threadx.metrics.server.mapper.UserPermissionMapper;
import com.threadx.metrics.server.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户权限实现
 *
 * @author huangfukexing
 * @date 2023/6/1 14:43
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements PermissionService {
}
