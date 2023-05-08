package com.threadx.metrics.server.constant;

/**
 * *************************************************<br/>
 * redis缓存主键<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/5/8 17:00
 */
public interface RedisCacheKey {

    String INSTANCE_ID_CACHE = "threadx:cache:instance:%s:%s";
    String SERVER_ID_CACHE = "threadx:cache:server:%s";
}
