package com.threadx.metrics.server.async;

import cn.hutool.json.JSONUtil;
import com.threadx.communication.common.agreement.packet.ThreadPoolCollectMessage;
import lombok.extern.slf4j.Slf4j;

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
    private final String ipaddress;

    public ThreadPoolDataRunnable(ThreadPoolCollectMessage threadPoolCollectMessage, String ipaddress) {
        this.threadPoolCollectMessage = threadPoolCollectMessage;
        this.ipaddress = ipaddress;
    }

    @Override
    public void run() {
        log.info("数据：{}, 数据来源: {}",JSONUtil.toJsonStr(threadPoolCollectMessage), ipaddress);
    }
}
