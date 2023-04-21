package com.threadx.metrics.server.async;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.threadx.communication.common.agreement.packet.ThreadPoolCollectMessage;
import com.threadx.metrics.server.constant.RedisKeyConstant;
import com.threadx.metrics.server.entity.ThreadPoolData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 线程池数据异步处理器
 *
 * @author huangfukexing
 * @date 2023/4/18 12:42
 */
@Slf4j
public class ThreadPoolDataRunnable implements Runnable {

    /**
     * 线程池收集的数据
     */
    private final ThreadPoolCollectMessage threadPoolCollectMessage;
    /**
     * 数据来源
     */
    private final String ipAddress;

    public ThreadPoolDataRunnable(ThreadPoolCollectMessage threadPoolCollectMessage, String ipAddress) {
        this.threadPoolCollectMessage = threadPoolCollectMessage;
        this.ipAddress = ipAddress;
    }

    @Override
    public void run() {
        ThreadPoolData threadPoolData = new ThreadPoolData();
        BeanUtil.copyProperties(threadPoolCollectMessage, threadPoolData);
        threadPoolData.setAddress(ipAddress);
        //写入redis
        StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        redisTemplate.opsForList().leftPush(RedisKeyConstant.THREAD_POOL_DATA, JSONUtil.toJsonStr(threadPoolData));
    }
}
