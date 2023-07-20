package com.threadx.metrics.server.service;

import java.util.List;

/**
 * 用户权限服务
 *
 * @author huangfukexing
 * @date 2023/6/1 14:53
 */
public interface UserMenuService {
    /**
     * 查询全部的菜单信息 根据用户的id 查询用户名下的菜单信息
     *
     * @param userId 用户的id信息
     * @return 用户名下所有的菜单id
     */
    List<Long> findAllByUserId(Long userId);

    /**
     * 删除根据用户的id
     * @param userId 用户的id
     */
    void deleteByUserId(Long userId);
}
