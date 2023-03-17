package com.threadx.thread;

import com.threadx.log.Logger;
import com.threadx.log.factory.ThreadXLoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * threadx的线程工厂
 *
 * @author huangfukexing
 * @date 2023/3/17 15:48
 */
public class ThreadXThreadFactory implements ThreadFactory {

    private final AtomicInteger IDX = new AtomicInteger(0);

    /**
     * 线程名称前缀
     */
    private final String threadNamePrefix;

    public ThreadXThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Logger logger = ThreadXLoggerFactory.getLogger(ThreadXThreadFactory.class);
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        thread.setName(String.format("%s-%s", threadNamePrefix, IDX.getAndIncrement()));
        thread.setUncaughtExceptionHandler((t, e) -> {
            logger.error("thread {} error , error message is {}", t.getName(), e.getMessage());
        });
        return thread;
    }
}
