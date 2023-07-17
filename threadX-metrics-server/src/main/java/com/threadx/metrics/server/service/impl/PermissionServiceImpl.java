package com.threadx.metrics.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.common.code.LoginExceptionCode;
import com.threadx.metrics.server.common.context.LoginContext;
import com.threadx.metrics.server.common.exceptions.LoginException;
import com.threadx.metrics.server.constant.RedisCacheKey;
import com.threadx.metrics.server.entity.Permission;
import com.threadx.metrics.server.mapper.PermissionMapper;
import com.threadx.metrics.server.service.PermissionService;
import com.threadx.metrics.server.service.UserPermissionService;
import com.threadx.metrics.server.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 权限实现
 *
 * @author huangfukexing
 * @date 2023/6/1 14:43
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private final StringRedisTemplate redisTemplate;
    private final UserPermissionService userPermissionService;

    public PermissionServiceImpl(StringRedisTemplate redisTemplate, UserPermissionService userPermissionService) {
        this.redisTemplate = redisTemplate;
        this.userPermissionService = userPermissionService;
    }

    @Override
    public List<Permission> findThisUserPermission() {
        UserDto userData = LoginContext.getUserData();
        if(userData == null) {
            throw new LoginException(LoginExceptionCode.USER_NOT_LOGIN_ERROR);
        }
        Long userId = userData.getId();
        //先查询缓存
        String permissionCacheKey = String.format(RedisCacheKey.USER_PERMISSION_CACHE, userId);
        String permissionData = redisTemplate.opsForValue().get(permissionCacheKey);
        List<Permission> permissions = new ArrayList<>();
        if(StrUtil.isNotBlank(permissionData)) {
            permissions = JSONUtil.toList(permissionData, Permission.class);
        }else {
            //查询中间表
            List<Long> permissionServiceByUserId = userPermissionService.findByUserId(userId);
            if(CollUtil.isNotEmpty(permissionServiceByUserId)) {
                QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
                queryWrapper.in("id", permissionServiceByUserId);
                permissions = baseMapper.selectList(queryWrapper);
            }
        }
        if(CollUtil.isNotEmpty(permissions)) {
            redisTemplate.opsForValue().set(permissionCacheKey, JSONUtil.toJsonStr(permissions), 1, TimeUnit.HOURS);
        }
        return permissions;
    }
}
