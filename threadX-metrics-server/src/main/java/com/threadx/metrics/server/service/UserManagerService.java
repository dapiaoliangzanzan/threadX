package com.threadx.metrics.server.service;

import com.threadx.metrics.server.dto.UserInfoDto;
import com.threadx.metrics.server.dto.UserLoginDto;
import com.threadx.metrics.server.entity.User;

/**
 * 用户服务
 *
 * @author huangfukexing
 * @date 2023/6/1 07:53
 */
public interface UserManagerService {

    /**
     * 保存用户
     *
     * @param userInfoDto 用户信息
     */
    void saveUser(UserInfoDto userInfoDto);

    /**
     * 管理员修改用户信息
     *
     * @param userInfoDto 用户信息
     */
    void updateUser(UserInfoDto userInfoDto);
}
