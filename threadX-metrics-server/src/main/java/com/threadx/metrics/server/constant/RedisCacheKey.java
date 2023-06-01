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

    /**
     * 用户的token令牌缓存
     */
    String USER_TOKEN_CACHE = "threadx:cache:token:%s";
    /**
     * 实例id缓存主键， 占位符为 服务名称   实例名称
     */
    String INSTANCE_ID_CACHE = "threadx:cache:instance:%s:%s";
    /**
     * 服务id缓存主键， 占位符为 服务名称
     */
    String SERVER_ID_CACHE = "threadx:cache:server:%s";

    /**
     * 线程池最后一次的数据缓存  占位符为  服务名称  实例名称  实例地址  线程池名称
     */
    String THREAD_POOL_LAST_DATA_CACHE = "threadx:cache:pool:last:%s:%s:%s:%s";
}
