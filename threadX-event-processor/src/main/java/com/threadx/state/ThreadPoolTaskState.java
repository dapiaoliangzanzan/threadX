package com.threadx.state;

import com.threadx.cache.ThreadPoolIndexCache;
import com.threadx.cache.ThreadPoolIndexData;
import com.threadx.cache.ThreadPoolTaskCache;
import com.threadx.log.Logger;
import com.threadx.log.factory.ThreadXLoggerFactory;
import com.threadx.publisher.events.ThreadPoolExecutorThreadTaskState;
import com.threadx.utils.ThreadXThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池任务
 *
 * @author huangfukexing
 * @date 2023/3/22 14:00
 */
public class ThreadPoolTaskState {

    /**
     * 初始化任务，调用时机是任务刚被提交的时候
     *
     * @param command      提交的任务
     * @param executorPool 使用的线程池
     */
    public static void init(Runnable command, ThreadPoolExecutor executorPool) {
        String threadPoolId = ThreadXThreadPoolUtil.getObjectId(executorPool);
        ThreadPoolIndexData threadPoolIndexData = ThreadPoolIndexCache.getCache(threadPoolId);
        if(threadPoolIndexData != null) {
            Logger logger = ThreadXLoggerFactory.getLogger(ThreadPoolExecutorState.class);
            String threadPoolName = threadPoolIndexData.getThreadPoolName();
            String threadPoolGroupName = threadPoolIndexData.getThreadPoolGroupName();
            logger.debug("thread pool {} submit task.", threadPoolGroupName);
            //构建数据
            ThreadPoolExecutorThreadTaskState threadPoolExecutorThreadTaskState = new ThreadPoolExecutorThreadTaskState();
            threadPoolExecutorThreadTaskState.setSubmitTime(System.currentTimeMillis());
            String runnableId = ThreadXThreadPoolUtil.getObjectId(command);
            threadPoolExecutorThreadTaskState.setTaskId(runnableId);
            threadPoolExecutorThreadTaskState.setThreadPoolName(threadPoolName);
            threadPoolExecutorThreadTaskState.setThreadPoolGroupName(threadPoolGroupName);
            threadPoolExecutorThreadTaskState.setThreadPoolId(threadPoolId);
            //缓存数据
            ThreadPoolTaskCache.addCache(runnableId, threadPoolExecutorThreadTaskState);
            logger.debug("thread task init cache success.");
        }
    }
}
