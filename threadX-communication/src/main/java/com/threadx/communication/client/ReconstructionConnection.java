package com.threadx.communication.client;

import cn.hutool.core.collection.CollectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 重新建立连接的处理器
 *
 * @author huangfukexing
 * @date 2023/4/25 15:00
 */
public class ReconstructionConnection {

    private final static ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private final static Lock READ_LOCK = READ_WRITE_LOCK.readLock();
    private final static Lock WRITE_LOCK = READ_WRITE_LOCK.writeLock();

    private final static AtomicBoolean IS_START = new AtomicBoolean(false);

    private final static ScheduledThreadPoolExecutor RE_CONNECTION_POOL = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("re-Connection-Pool");
            return thread;
        }
    });
    /**
     * 活跃的连接
     */
    private static final Map<String, CommunicationClient> ACTIVE_CONNECTION = new ConcurrentHashMap<>();

    /**
     * 失效的连接
     */
    private static final Map<String, CommunicationClient> FAILURE_CONNECTION = new ConcurrentHashMap<>();


    /**
     * 关闭连接
     *
     * @param serverAddress 服务地址
     */
    public static void closeConnection(String serverAddress) {
        ACTIVE_CONNECTION.remove(serverAddress);
        FAILURE_CONNECTION.remove(serverAddress);
    }

    /**
     * 新建一个有效的连接
     *
     * @param communicationClient 通信客户端
     */
    public static void newActiveConnection(CommunicationClient communicationClient) {
        WRITE_LOCK.lock();
        try {
            ACTIVE_CONNECTION.put(communicationClient.getServerAddress(), communicationClient);
            FAILURE_CONNECTION.remove(communicationClient.getServerAddress());
        } finally {
            WRITE_LOCK.unlock();
        }

    }

    /**
     * 将一个活跃的连接变为不可用，等待重连
     *
     * @param communicationClient 通信客户端
     */
    public static void confinementConnection(CommunicationClient communicationClient) {
        WRITE_LOCK.lock();
        try {
            ACTIVE_CONNECTION.remove(communicationClient.getServerAddress(), communicationClient);
            FAILURE_CONNECTION.put(communicationClient.getServerAddress(), communicationClient);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    /**
     * 返回所有活跃的连接
     *
     * @return 所有活跃的连接
     */
    public static Map<String, CommunicationClient> getAllActiveConnection() {
        READ_LOCK.lock();
        try {
            Map<String, CommunicationClient> communicationClientMap = new HashMap<>();
            if (CollectionUtil.isNotEmpty(ACTIVE_CONNECTION)) {
                ACTIVE_CONNECTION.forEach(communicationClientMap::put);
            }
            return communicationClientMap;
        } finally {
            READ_LOCK.unlock();
        }
    }

    /**
     * 自动重连
     */
    public static void reConnectionConfinementConnection() {
        boolean start = IS_START.get();
        if (!start) {
            IS_START.compareAndSet(false, true);
            RE_CONNECTION_POOL.scheduleAtFixedRate(() -> {
                try {
                    if (CollectionUtil.isNotEmpty(FAILURE_CONNECTION)) {
                        FAILURE_CONNECTION.forEach((key, value) -> value.reConnect());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 3, 3, TimeUnit.SECONDS);
        }
    }


}
