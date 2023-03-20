package com.threadx.constant;

/**
 * *************************************************<br/>
 * threadx配置信息<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/3/20 18:34
 */
public enum ThreadXPropertiesEnum {
    /**
     * 线程池采集间隔
     */
    THREAD_POOL_COLLECTION_INTERVAL("threadx.thread.pool.collection.interval", "3"),
    ;

    /**
     * 配置key
     */
    private final String key;
    /**
     * 默认值
     */
    private final String defaultValue;

    ThreadXPropertiesEnum(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
