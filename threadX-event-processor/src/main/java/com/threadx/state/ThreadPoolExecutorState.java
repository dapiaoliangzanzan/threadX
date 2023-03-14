package com.threadx.state;

import com.threadx.log.Logger;
import com.threadx.log.factory.ThreadXLoggerFactory;
import com.threadx.proxy.ThreadXThreadPoolExecutorProxy;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池状态
 *
 * @author huangfukexing
 * @date 2023/3/9 21:25
 */
public class ThreadPoolExecutorState implements Serializable {
    private static final long serialVersionUID = -8563010018920100713L;




    public static void init(ThreadPoolExecutor sourceThreadPoolExecutor) {
        Logger logger = ThreadXLoggerFactory.getLogger(ThreadPoolExecutorState.class);
        logger.info("拦截到线程池创建: {}", sourceThreadPoolExecutor.toString());
        //生成线程池的名称
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
        //获取调用的方法
        String className = stackTraceElement.getClassName();
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        String methodName = stackTraceElement.getMethodName();
        logger.info("className:{}, fileName:{}, lineNumber:{}, methodName:{}", className, fileName, lineNumber, methodName);
        String threadPoolName = String.format("%s#%s#%d", className, methodName, lineNumber);
        logger.warn("线程池的名称为：{}",  threadPoolName);
    }
}
