package com.threadx.publisher.events;

import com.threadx.publisher.ThreadXStatusEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 线程的任务状态，每一个对象唯一绑定一个任务对象，该任务对象生命周期与任务的生命周期相同！
 *
 * @author huangfukexing
 * @date 2023/3/9 18:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadPoolExecutorThreadTaskState implements ThreadXStatusEvent, Serializable {
    private static final long serialVersionUID = -8563010018920100713L;

    /**
     * 任务的id
     */
    private String taskId;

    /**
     * 线程池的Id
     */
    private String threadPoolId;
    /**
     * 线程池组的名称
     */
    private String threadPoolGroupName;

    /**
     * 线程池的名称
     */
    private String threadPoolName;

    /**
     * 被那一个线程执行的
     */
    private String threadName;

    /**
     * 该时间为任务被提交的时间，只要该任务被加载进线程池，这个时间就会被初始化
     */
    private long submitTime;

    /**
     * 任务开始运行的时间，注意，这里的开始时间是任务真正开始运行的时间，不是提交的时间，因为他可能被堆积在队列中
     */
    private long startTime;

    /**
     * 任务的结束时间，无奈他是正常结束，或者是异常，这个时间都会存在，当然，被拒绝的任务不在此列！
     */
    private long endTime;

    /**
     * 任务的执行耗时，该时间为 {@link ThreadPoolExecutorThreadTaskState#endTime} - {@link ThreadPoolExecutorThreadTaskState#startTime}
     */
    private long runIngConsumingTime;

    /**
     * 任务等待时间，该时间为 {@link ThreadPoolExecutorThreadTaskState#startTime} - {@link ThreadPoolExecutorThreadTaskState#submitTime}
     */
    private long waitTime;

    /**
     * 任务等待时间，该时间为 {@link ThreadPoolExecutorThreadTaskState#endTime} - {@link ThreadPoolExecutorThreadTaskState#submitTime}
     */
    private long consumingTime;

    /**
     * 当任务被拒绝策略执行的时候，该值为true,否则为false!
     */
    private boolean refuse;

    /**
     * 任务是否被执行成功，如果中途异常、被拒绝，该值都会被设置为false, 否则为true
     */
    private boolean success;

    /**
     * 任务的异常信息，当没有异常的时候，这个值为空！
     */
    private Throwable throwable;

}
