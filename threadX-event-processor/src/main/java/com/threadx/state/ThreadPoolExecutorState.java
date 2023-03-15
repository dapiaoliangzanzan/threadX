package com.threadx.state;

import com.threadx.log.Logger;
import com.threadx.log.factory.ThreadXLoggerFactory;
import com.threadx.utils.ConfirmCheckUtil;
import com.threadx.utils.ThreadXThreadPoolUtil;

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
        if(ConfirmCheckUtil.isIntercept()) {
            //生成线程池的名称
            String threadPoolGroupName = ThreadXThreadPoolUtil.generateThreadPoolGroupName();
            logger.info("线程池组的名称为：{}", threadPoolGroupName);
            logger.info("线程池的名称为：{}", ThreadXThreadPoolUtil.generateThreadPoolName(threadPoolGroupName, sourceThreadPoolExecutor));
            logger.info("线程池的堆栈快照为：\n{}", ThreadXThreadPoolUtil.getThreadPoolStack());
            logger.info("================================================================");
        }


    }
}
