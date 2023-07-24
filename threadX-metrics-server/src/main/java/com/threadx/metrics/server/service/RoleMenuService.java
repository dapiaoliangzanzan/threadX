package com.threadx.metrics.server.service;

import java.util.List;
import java.util.Set;

/**
 * 角色 菜单权限
 *
 * @author huangfukexing
 * @date 2023/7/24 10:40
 */
public interface RoleMenuService {

    /**
     * 查询所有的菜单id  根据角色的id
     *
     * @param roleIds 角色的id
     * @return 当前角色下 所有的菜单的id
     */
    Set<Long> findByRoleIds(List<Long> roleIds);
}
