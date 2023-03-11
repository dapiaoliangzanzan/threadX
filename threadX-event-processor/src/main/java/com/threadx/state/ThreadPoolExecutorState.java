package com.threadx.state;

import com.threadx.description.context.AgentContext;
import com.threadx.log.Logger;
import com.threadx.log.factory.ThreadXLoggerFactory;

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
        Logger logger = ThreadXLoggerFactory.getLogger(ThreadPoolExecutorState.class);
        logger.info("拦截到线程池创建: {}", sourceThreadPoolExecutor.toString());
        int activeCount = sourceThreadPoolExecutor.getActiveCount();
        int corePoolSize = sourceThreadPoolExecutor.getCorePoolSize();
        String name = sourceThreadPoolExecutor.getQueue().getClass().getName();
        String rn = sourceThreadPoolExecutor.getRejectedExecutionHandler().getClass().getName();
        int maximumPoolSize = sourceThreadPoolExecutor.getMaximumPoolSize();
        logger.info("当前线程池的核心线程数为：{}, 活跃线程数为:{}, 最大线程数为：{}, 队列名称为：{}, 拒绝策略为: {}", corePoolSize, activeCount,maximumPoolSize, name, rn);

    }
}
