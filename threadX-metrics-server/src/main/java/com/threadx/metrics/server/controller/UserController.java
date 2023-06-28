package com.threadx.metrics.server.controller;

import com.threadx.metrics.server.common.annotations.GlobalResultPackage;
import com.threadx.metrics.server.common.annotations.Log;
import com.threadx.metrics.server.common.annotations.Login;
import com.threadx.metrics.server.common.annotations.UserPermission;
import com.threadx.metrics.server.dto.UserInfoDto;
import com.threadx.metrics.server.dto.UserLoginDto;
import com.threadx.metrics.server.enums.LogEnum;
import com.threadx.metrics.server.service.UserService;
import com.threadx.metrics.server.vo.LoginUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户操作
 *
 * @author huangfukexing
 * @date 2023/6/1 09:38
 */
@RestController
@GlobalResultPackage
@Api(tags = "用户常规操作")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Log(LogEnum.USER_LOGIN)
    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public LoginUserVo login(@RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }


    @Login
    @Log(LogEnum.USER_LOGOUT)
    @ApiOperation(value = "用户登出")
    @PostMapping("logout")
    public void logout(){
        userService.logout();
    }
}
