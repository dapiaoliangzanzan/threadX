package com.threadx.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * *************************************************<br/>
 * 业务线程使用的拒绝策略包装<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/3/20 18:11
 */
public class BusinessThreadXRejectedExecutionHandler implements RejectedExecutionHandler {
    private final RejectedExecutionHandler rejectedExecutionHandler;
    private final AtomicLong COUNT;

    public BusinessThreadXRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        this.COUNT = new AtomicLong(0);
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            rejectedExecutionHandler.rejectedExecution(r, executor);
        }finally {
            //拒绝计数
            COUNT.incrementAndGet();
        }

    }

    public RejectedExecutionHandler getRejectedExecutionHandler() {
        return rejectedExecutionHandler;
    }

    public Long getCount() {
        return COUNT.get();
    }

    @Override
    public String toString() {
        return rejectedExecutionHandler.toString();
    }
}
