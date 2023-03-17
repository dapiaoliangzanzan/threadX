package com.threadx.publisher.events;

import com.threadx.publisher.ThreadXStatusEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.concurrent.*;

/**
 * 线程池指标事件源
 *
 * @author huangfukexing
 * @date 2023/3/17 14:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadPoolExecutorStatusEvent implements ThreadXStatusEvent, Serializable {

    private Integer corePoolSize;

    /**
     * 最大可执行任务的线程数
     */
    private Integer maximumPoolSize;

    /**
     * 当前活跃的线程数
     */
    private Integer activeCount;

    /**
     * 当前线程池的线程数量  包含没有执行任务的线程还没有来得及被销毁的非核心线程
     */
    private Integer thisThreadCount;



    /**
     * 曾将达到的最大的线程数  历史信息
     */
    private Integer largestPoolSize;

    /**
     * 拒绝执行的次数
     */
    private Integer rejectedCount;

    /**
     * 堆积的、执行中的、已经完成的任务的总和
     */
    private Integer taskCount;


    /**
     * 已经完成的数量
     */
    private Integer completedTaskCount;

    /**
     * 队列类型
     */
    private String queueType;

    /**
     * 拒绝策略
     */
    private String rejectedType;
    /**
     * 毫秒
     * 线程空闲
     */
    private long keepAliveTime;


    public static void main(String[] args) {
        ThreadPoolExecutor e = new ThreadPoolExecutor(1, 1, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(102));
        //使用的队列类型
        BlockingQueue<Runnable> queue = e.getQueue();
        //最大的线程数量
        int maximumPoolSize = e.getMaximumPoolSize();
        //核心的线程数量
        int corePoolSize = e.getCorePoolSize();
        //活跃的线程数量
        int activeCount = e.getActiveCount();
        //拒绝策略
        RejectedExecutionHandler rejectedExecutionHandler = e.getRejectedExecutionHandler();
        //线程过期事件
        long keepAliveTime = e.getKeepAliveTime(TimeUnit.MILLISECONDS);
        //完成的任务总量
        long completedTaskCount = e.getCompletedTaskCount();
        //本次进程中，他执行过的任务的总和 包含 未执行的、堆积的、执行中的、执行完成的
        long taskCount = e.getTaskCount();
        //曾经达到的最大线程数
        int largestPoolSize = e.getLargestPoolSize();
        //当前线程池中的线程数
        int poolSize = e.getPoolSize();
    }
}
