package com.threadx.metrics.server.service;

import java.util.List;

/**
 * 用户权限中间服务
 *
 * @author huangfukexing
 * @date 2023/6/1 14:43
 */
public interface UserPermissionService {

    /**
     * 查询 根据用户ID查询权限信息
     *
     * @param userId 用户的ID
     * @return 权限信息的Id
     */
    List<Long> findByUserId(Long userId);
}
