package com.threadx.state;

/**
 * 线程池采集
 *
 * @author huangfukexing
 * @date 2023/3/9 18:02
 */
public class ThreadPoolExecutorCollect {
    /**
     * 记录当前线程的状态
     */
    private static final ThreadLocal<ThreadPoolExecutorThreadTaskState> STATE_CACHE = new ThreadLocal<>();

    /**
     * 初始化线程池状态
     */
    public static void init() {
        STATE_CACHE.set(new ThreadPoolExecutorThreadTaskState());
    }

    /**
     * 获取线程状态缓存
     *
     * @return 线程状态
     */
    public static ThreadPoolExecutorThreadTaskState getStateCache() {
        return STATE_CACHE.get();
    }

    /**
     * 销毁线程状态记录
     */
    public static void destroy() {
        STATE_CACHE.remove();
    }
}
