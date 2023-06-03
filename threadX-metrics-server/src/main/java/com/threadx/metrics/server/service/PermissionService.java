package com.threadx.metrics.server.service;

import com.threadx.metrics.server.entity.Menu;
import com.threadx.metrics.server.entity.Permission;

import java.util.List;

/**
 * 权限服务
 *
 * @author huangfukexing
 * @date 2023/6/1 14:43
 */
public interface PermissionService {


    /**
     * 查询权限信息 查询当前用户的权限信息
     *
     * @return 返回符合调价的菜单
     */
    List<Permission> findThisUserPermission();
}
