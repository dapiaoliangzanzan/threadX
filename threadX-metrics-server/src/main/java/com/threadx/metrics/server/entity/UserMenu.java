package com.threadx.metrics.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户、菜单关联表
 *
 * @author huangfukexing
 * @date 2023/6/1 14:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_menu")
public class UserMenu implements Serializable {
    private static final long serialVersionUID = -9097990324823705007L;

    /**
     * 用户的id
     */
    private Long userId;

    /**
     * 菜单的id
     */
    private Long menuId;
}
