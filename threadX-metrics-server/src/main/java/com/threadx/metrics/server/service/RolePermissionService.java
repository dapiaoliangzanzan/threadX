package com.threadx.metrics.server.service;

import java.util.List;
import java.util.Set;

/**
 * 角色权限服务
 *
 * @author huangfukexing
 * @date 2023/7/24 10:40
 */
public interface RolePermissionService {

    /**
     * 查询所有的权限id  根据角色的id
     *
     * @param roleIds 角色的id
     * @return 当前角色下 所有的权限的id
     */
    Set<Long> findByRoleIds(List<Long> roleIds);
}
