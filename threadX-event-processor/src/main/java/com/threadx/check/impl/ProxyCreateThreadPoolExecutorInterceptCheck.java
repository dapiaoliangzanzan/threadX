package com.threadx.check.impl;

import com.threadx.check.ThreadPoolExecutorInterceptCheck;
import com.threadx.proxy.ThreadXThreadPoolExecutorProxy;

/**
 * 是不是代理新建的
 *
 * @author huangfukexing
 * @date 2023/3/14 14:27
 */
public class ProxyCreateThreadPoolExecutorInterceptCheck extends ThreadPoolExecutorInterceptCheck {


    @Override
    public boolean doInterceptCheck(StackTraceElement[] stackTrace) {
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if(ThreadXThreadPoolExecutorProxy.class.getName().equals(className) && "<init>".equals(methodName)){
                return false;
            }
        }
        return true;
    }
}
