package com.threadx.metrics.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.entity.UserPermission;
import com.threadx.metrics.server.mapper.UserPermissionMapper;
import com.threadx.metrics.server.service.UserPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户权限实现
 *
 * @author huangfukexing
 * @date 2023/6/1 14:43
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {


    @Override
    public List<Long> findByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        QueryWrapper<UserPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserPermission> userPermissions = baseMapper.selectList(queryWrapper);
        return userPermissions.stream().map(UserPermission::getPermissionId).collect(Collectors.toList());
    }
}
