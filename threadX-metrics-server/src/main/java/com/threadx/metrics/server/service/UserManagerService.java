package com.threadx.metrics.server.service;

import com.threadx.metrics.server.conditions.UserPageConditions;
import com.threadx.metrics.server.dto.UserInfoDto;
import com.threadx.metrics.server.dto.UserLoginDto;
import com.threadx.metrics.server.entity.User;
import com.threadx.metrics.server.vo.ThreadxPage;
import com.threadx.metrics.server.vo.UserVo;

import java.util.Collection;
import java.util.List;

/**
 * 用户服务
 *
 * @author huangfukexing
 * @date 2023/6/1 07:53
 */
public interface UserManagerService {

    /**
     * 查询根据用户的id的集合
     *
     * @param userIds 用户的id集合
     * @return 用户信息
     */
    List<User> findByUserIds(Collection<Long> userIds);

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

    /**
     * 根据条件查询所有的用户
     *
     * @param userPageConditions 查询条件
     * @return 返回的分页结果集
     */
    ThreadxPage<UserVo> findAllUser(UserPageConditions userPageConditions);

    /**
     * 冻结用户
     *
     * @param userId 用户的id
     */
    void freezeUser(Long userId);

    /**
     * 解除冻结用户
     *
     * @param userId 用户的id
     */
    void unsealUser(Long userId);

    /**
     * 强制删除用户 包含所有的依赖关系以及权限信息和日志信息
     *
     * @param userId 用户的id
     */
    void forceDeleteUser(Long userId);
}
