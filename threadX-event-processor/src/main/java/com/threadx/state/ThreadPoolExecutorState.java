package com.threadx.state;

import com.threadx.cache.ThreadPoolIndexData;
import com.threadx.cache.ThreadPoolWeakReferenceCache;
import com.threadx.log.Logger;
import com.threadx.log.factory.ThreadXLoggerFactory;
import com.threadx.thread.BusinessThreadXRejectedExecutionHandler;
import com.threadx.utils.ConfirmCheckUtil;

import java.io.Serializable;
import java.util.concurrent.RejectedExecutionHandler;
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
        if(ConfirmCheckUtil.isIntercept()) {
            RejectedExecutionHandler rejectedExecutionHandler = sourceThreadPoolExecutor.getRejectedExecutionHandler();
            logger.info("source RejectedExecutionHandler: {}", rejectedExecutionHandler);
            BusinessThreadXRejectedExecutionHandler newRejectedExecutionHandler = new BusinessThreadXRejectedExecutionHandler(rejectedExecutionHandler);
            sourceThreadPoolExecutor.setRejectedExecutionHandler(newRejectedExecutionHandler);
            logger.info("Package rejection strategy: {}", newRejectedExecutionHandler);
            ThreadPoolIndexData threadPoolIndexData = ThreadPoolWeakReferenceCache.setCache(sourceThreadPoolExecutor);
            logger.info("add thread Pool index data, thread pool name is {}， thread pool group name is {}", threadPoolIndexData.getThreadPoolName(), threadPoolIndexData.getThreadPoolGroupName());
        }


    }
}
