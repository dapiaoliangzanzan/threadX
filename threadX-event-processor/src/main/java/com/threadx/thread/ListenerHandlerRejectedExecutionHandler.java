package com.threadx.thread;

import com.threadx.log.Logger;
import com.threadx.log.factory.ThreadXLoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 监听程序的拒绝程序
 *
 * @author huangfukexing
 * @date 2023/3/17 16:13
 */
public class ListenerHandlerRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        Logger logger = ThreadXLoggerFactory.getLogger(ListenerHandlerRejectedExecutionHandler.class);
        logger.warn("Check the accumulation of thread pool collection event handlers to reach the maximum threshold");
        if (!executor.isShutdown()) {
            r.run();
        }else {
            logger.error("thread pool Shutdown, ListenerHandlerRejectedExecutionHandler Refuse to deal with !!!");
        }
    }
}
