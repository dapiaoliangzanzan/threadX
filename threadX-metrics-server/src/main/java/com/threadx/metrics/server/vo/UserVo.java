package com.threadx.metrics.server.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户映射信息
 *
 * @author huangfukexing
 * @date 2023/6/1 07:53
 */
@Data
@ApiModel(description = "用户的基础信息", value = "用户的基础信息")
public class UserVo implements Serializable {
    private static final long serialVersionUID = 7045262567616349898L;

    /**
     * 用户的id
     */
    @ApiModelProperty(name = "id", value = "用户的id")
    private Long id;

    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "nickName", value = "用户昵称")
    private String nickName;

    /**
     * 用户的登录名
     */
    @ApiModelProperty(name = "userName", value = "用户的登录名")
    private String userName;

    /**
     * 用户的邮箱
     */
    @ApiModelProperty(name = "email", value = "用户的邮箱")
    private String email;

    /**
     * 用户的创建时间
     */
    @ApiModelProperty(name = "createTime", value = "用户的创建时间")
    private Long createTime;

    /**
     * 用户的修改时间
     */
    @ApiModelProperty(name = "updateTime", value = "用户的修改时间")
    private Long updateTime;
}
