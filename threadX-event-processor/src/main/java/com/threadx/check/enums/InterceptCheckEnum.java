package com.threadx.check.enums;

import com.threadx.check.ThreadPoolExecutorInterceptCheck;
import com.threadx.check.impl.ProxyCreateThreadPoolExecutorInterceptCheck;
import com.threadx.log.Logger;
import com.threadx.log.factory.ThreadXLoggerFactory;
import com.threadx.utils.ReflectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拦截检查的插件枚举
 *
 * @author huangfukexing
 * @date 2023/3/14 14:31
 */
public enum InterceptCheckEnum {
    /**
     * 代理新建的检查
     */
    PROXY_CREATE_CHECK("ProxyCreateThreadPoolExecutorInterceptCheck", ProxyCreateThreadPoolExecutorInterceptCheck.class);
    ;
    private final String checkName;
    private final Class<? extends ThreadPoolExecutorInterceptCheck> interceptCheckClass;

    InterceptCheckEnum(String checkName, Class<? extends ThreadPoolExecutorInterceptCheck> interceptCheckClass) {
        this.checkName = checkName;
        this.interceptCheckClass = interceptCheckClass;
    }

    /**
     * 执行所有检查
     * @return 是否所有的检查全部通过
     */
    public static boolean allCheck() throws Exception {
        Logger logger = ThreadXLoggerFactory.getLogger(InterceptCheckEnum.class);
        InterceptCheckEnum[] interceptCheckEnums = InterceptCheckEnum.values();
        for (InterceptCheckEnum interceptCheckEnum : interceptCheckEnums) {
            String checkName = interceptCheckEnum.checkName;
            Class<? extends ThreadPoolExecutorInterceptCheck> interceptCheckClass = interceptCheckEnum.interceptCheckClass;
            ThreadPoolExecutorInterceptCheck interceptCheck = ReflectionUtils.newInstanceConstructor(interceptCheckClass);
            logger.warn("check name: {}" , checkName);
            if (!interceptCheck.interceptCheck()) {
                logger.warn("check name {} not Pass the verification Result" , checkName);
                return false;
            }
            logger.warn("check name {} Pass the verification Result" , checkName);
        }
        return true;
    }
}
