package com.threadx.metrics.server.controller;

import com.threadx.metrics.server.common.annotations.GlobalResultPackage;
import com.threadx.metrics.server.common.annotations.Login;
import com.threadx.metrics.server.common.annotations.UserPermission;
import com.threadx.metrics.server.dto.UserInfoDto;
import com.threadx.metrics.server.service.UserManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * *************************************************<br/>
 * 用户管理操作<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/6/3 13:56
 */
@RestController
@GlobalResultPackage
@Api(tags = "用户管理操作")
@RequestMapping("/manager/user")
public class UserManagerController {

    private final UserManagerService userManagerService;

    public UserManagerController(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }


    @Login
    @UserPermission("add:user")
    @ApiOperation(value = "添加用户")
    @PostMapping("addUser")
    public void addUser(@RequestBody UserInfoDto userInfoDto){
        userManagerService.saveUser(userInfoDto);
    }

    @Login
    @UserPermission("update:user")
    @ApiOperation(value = "修改用户信息")
    @PostMapping("updateUser")
    public void updateUser(@RequestBody UserInfoDto userInfoDto){
        userManagerService.updateUser(userInfoDto);
    }
}
