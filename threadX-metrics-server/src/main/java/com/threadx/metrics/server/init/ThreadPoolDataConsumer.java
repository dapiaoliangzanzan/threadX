package com.threadx.metrics.server.init;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.threadx.metrics.server.entity.ThreadPoolData;
import com.threadx.metrics.server.service.ThreadPoolDataService;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * *************************************************<br/>
 * 线程池数据消费者<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/4/22 15:25
 */
@Slf4j
@Component("threadPoolDataConsumer")
public class ThreadPoolDataConsumer implements InitializingBean, DisposableBean {
    private final static String QUEUE_SIZE = System.getProperties().getProperty("thread.pool.data.queue.size", "4096");
    private final static MpscArrayQueue<ThreadPoolData> THREAD_POOL_DATA = new MpscArrayQueue<>(Integer.parseInt(QUEUE_SIZE));
    private final static AtomicBoolean IS_START = new AtomicBoolean(false);
    private final static ScheduledThreadPoolExecutor THREAD_POOL = new ScheduledThreadPoolExecutor(1, r -> {
        Thread thread = new Thread(r);
        thread.setName("ThreadPoolDataConsumer");
        return thread;
    });

    private ScheduledFuture<?> scheduledFuture = null;


    private final ThreadPoolDataService threadPoolDataService;

    public ThreadPoolDataConsumer(ThreadPoolDataService threadPoolDataService) {
        this.threadPoolDataService = threadPoolDataService;
    }

    @Override
    public void destroy() throws Exception {
        if(scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        THREAD_POOL_DATA.clear();
        IS_START.compareAndSet(false, true);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        IS_START.compareAndSet(false, true);
        scheduledFuture = THREAD_POOL.scheduleAtFixedRate(() -> {
            try {
                List<ThreadPoolData> elements = new ArrayList<>();
                MessagePassingQueue.Consumer<ThreadPoolData> consumer = elements::add;
                THREAD_POOL_DATA.drain(consumer, 50);
                //批量入库
                if (CollUtil.isNotEmpty(elements)) {
                    threadPoolDataService.batchSave(elements);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }, 5, 2, TimeUnit.SECONDS);
    }

    public static void pushData(ThreadPoolData threadPoolData) {
        if (IS_START.get()) {
            if (!THREAD_POOL_DATA.offer(threadPoolData)) {
                log.warn("Existential task dropping {}, Please set the parameter -Dthread.pool.data.queue.size=value(value greater than {})", JSONUtil.toJsonStr(threadPoolData), QUEUE_SIZE);
            }
        }else {
            log.error("com.threadx.metrics.server.init.ThreadPoolDataConsumer spring bean Be destroyed.");
        }
    }
}
