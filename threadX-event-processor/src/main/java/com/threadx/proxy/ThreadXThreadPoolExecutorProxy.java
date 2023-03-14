package com.threadx.proxy;

import java.util.concurrent.*;

/**
 * 线程池的代理对象
 *
 * @author huangfukexing
 * @date 2023/3/14 14:15
 */
public class ThreadXThreadPoolExecutorProxy extends ThreadPoolExecutor {
    /**
     * 父类对象
     */
    private final ThreadPoolExecutor parent;

    public ThreadXThreadPoolExecutorProxy(ThreadPoolExecutor parent) {
        super(parent.getCorePoolSize(), parent.getMaximumPoolSize(), parent.getKeepAliveTime(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS, parent.getQueue(), parent.getThreadFactory(), parent.getRejectedExecutionHandler());
        this.parent = parent;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("我进入到了bufau的方法哈哈哈哈哈哈哈，拦截成功啦，很开心");
        super.beforeExecute(t, r);
    }
}
