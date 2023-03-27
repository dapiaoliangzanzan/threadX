package com.threadx.publisher.events;

import com.threadx.publisher.ThreadXStatusEvent;
import com.threadx.utils.ThreadXThrowableMessageUtil;
import lombok.*;

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
@ToString
@EqualsAndHashCode
public class ThreadPoolExecutorThreadTaskState implements ThreadXStatusEvent, Serializable {
    private static final Long serialVersionUID = -8563010018920100713L;

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
    private Long submitTime;

    /**
     * 任务开始运行的时间，注意，这里的开始时间是任务真正开始运行的时间，不是提交的时间，因为他可能被堆积在队列中
     */
    private Long startTime;

    /**
     * 任务的结束时间，无奈他是正常结束，或者是异常，这个时间都会存在，当然，被拒绝的任务不在此列！
     */
    private Long endTime;

    /**
     * 任务的执行耗时，该时间为 {@link ThreadPoolExecutorThreadTaskState#endTime} - {@link ThreadPoolExecutorThreadTaskState#startTime}
     */
    private Long runIngConsumingTime;

    /**
     * 任务等待时间，该时间为 {@link ThreadPoolExecutorThreadTaskState#startTime} - {@link ThreadPoolExecutorThreadTaskState#submitTime}
     */
    private Long waitTime;

    /**
     * 任务总耗时，该时间为 {@link ThreadPoolExecutorThreadTaskState#endTime} - {@link ThreadPoolExecutorThreadTaskState#submitTime}
     */
    private Long consumingTime;

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
    private String throwable;


    /**
     * 计算任务的耗时时间  包含  等待时间   运行耗时   总耗时信息
     */
    public void computingTime(){
        long submitTime = this.getSubmitTime() == null ? System.currentTimeMillis() : this.getSubmitTime();
        //计算任务的运行消耗时间
        this.setRunIngConsumingTime(endTime - this.getStartTime());
        //计算任务的等待时间
        this.setWaitTime(this.getStartTime() - submitTime);
        //计算任务的总耗时
        this.setConsumingTime(endTime - this.getSubmitTime());
    }

    public void setThrowable(Throwable throwable) {
        if(throwable != null) {
            this.throwable = ThreadXThrowableMessageUtil.messageRead(throwable);
        }

    }
}
