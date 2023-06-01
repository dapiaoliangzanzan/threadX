package com.threadx.metrics.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户权限关联表
 *
 * @author huangfukexing
 * @date 2023/6/1 14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_permission")
public class UserPermission implements Serializable {
    private static final long serialVersionUID = 563074028991697806L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 权限id
     */
    private Long permissionId;
}
