package com.threadx.metrics.server.service;

import java.util.List;

/**
 * 用户角色服务
 *
 * @author huangfukexing
 * @date 2023/7/24 10:28
 */
public interface UserRoleService {

    /**
     * 根据当前用户的id 查询指定用户下的所有的角色id
     *
     * @param userId 要查询的用户的id
     * @return 角色的id
     */
    List<Long> findRoleIdByUserId(Long userId);

    /**
     * 根据传递的用户信息 删除用户下的角色信息
     * @param userId 用户的id
     */
    void deleteByUserId(Long userId);
}
