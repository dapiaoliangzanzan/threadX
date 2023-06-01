package com.threadx.metrics.server.common.context;

import com.threadx.metrics.server.common.code.LoginExceptionCode;
import com.threadx.metrics.server.common.exceptions.LoginException;
import com.threadx.metrics.server.vo.UserVo;

/**
 * 登录上下文
 *
 * @author huangfukexing
 * @date 2023/6/1 15:36
 */
public class LoginContext {

    private final static ThreadLocal<UserVo> USER_CONTEXT = new ThreadLocal<>();

    /**
     * 设置用户上下文
     *
     * @param userVo 用户信息
     */
    public static void setUserData(UserVo userVo) {
        USER_CONTEXT.set(userVo);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    public static UserVo getUserData() {
        UserVo userVo = USER_CONTEXT.get();
        if(userVo == null) {
            throw new LoginException(LoginExceptionCode.USER_NOT_LOGIN_ERROR);
        }
        return userVo;
    }

    /**
     * 删除本次用户信息
     */
    public static void remove() {
        USER_CONTEXT.remove();
    }
}
