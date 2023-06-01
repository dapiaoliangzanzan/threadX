package com.threadx.metrics.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.threadx.metrics.server.entity.Permission;
import com.threadx.metrics.server.entity.UserPermission;
import org.springframework.stereotype.Repository;

/**
 * 用户权限数据库操作
 *
 * @author huangfukexing
 * @date 2023/6/1 14:42
 */
@Repository
public interface UserPermissionMapper extends BaseMapper<UserPermission> {
}
