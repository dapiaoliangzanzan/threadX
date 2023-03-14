package com.threadx.check;

/**
 * 线程池代理验证
 *
 * @author huangfukexing
 * @date 2023/3/14 14:24
 */
public abstract class ThreadPoolExecutorInterceptCheck {


    /**
     * 拦截检查
     *
     * @return 是否拦截
     */
    public boolean interceptCheck() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //获取最后一层堆栈信息
        return doInterceptCheck(stackTrace);
    }

    /**
     * 是否能够拦截一个检查
     *
     * @param stackTrace 堆栈信息的最后一个
     * @return 是否能够拦截  true拦截  false不拦截
     */
    public abstract boolean doInterceptCheck(StackTraceElement[] stackTrace);
}
