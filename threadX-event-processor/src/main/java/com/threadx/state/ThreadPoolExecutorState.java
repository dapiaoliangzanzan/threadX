package com.threadx.state;

import com.threadx.description.context.AgentContext;

import java.io.Serializable;
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
        System.out.println("尝试获取代理程序类加载器：" + AgentContext.getAgentClassLoader());
        System.out.println("拦截到线程池创建：" + sourceThreadPoolExecutor.toString());
    }
}
