package com.threadx.metrics.tms;

import cn.hutool.core.bean.BeanUtil;
import com.threadx.communication.client.CommunicationClient;
import com.threadx.communication.client.config.ClientConfig;
import com.threadx.communication.common.agreement.packet.ThreadPoolCollectMessage;
import com.threadx.communication.common.agreement.packet.ThreadPoolTaskCollectMessage;
import com.threadx.metrics.ThreadPoolExecutorData;
import com.threadx.metrics.ThreadTaskExecutorData;
import com.threadx.metrics.api.MetricsOutApi;

import java.util.List;

/**
 * ************************************************<br/>
 * threadX 指标收集服务类型<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/4/9 14:55
 */
public class TmsMetricsOut implements MetricsOutApi {

    public static final String METRICS_OUT = "tms";

    private  CommunicationClient communicationClient;
    @Override
    public void init() {
        communicationClient = new CommunicationClient(new ClientConfig("127.0.0.1", 9999, "test-server","instance"));
    }

    @Override
    public void outThreadPoolMetricsData(ThreadPoolExecutorData metricsData) {
        ThreadPoolCollectMessage threadPoolCollectMessage = new ThreadPoolCollectMessage();
        BeanUtil.copyProperties(metricsData, threadPoolCollectMessage);
        communicationClient.asyncSendMessage(threadPoolCollectMessage);
    }

    @Override
    public void outThreadTaskMetricsData(ThreadTaskExecutorData metricsData) {
        ThreadPoolTaskCollectMessage threadPoolTaskCollectMessage = new ThreadPoolTaskCollectMessage();
        BeanUtil.copyProperties(metricsData, threadPoolTaskCollectMessage);
        communicationClient.asyncSendMessage(threadPoolTaskCollectMessage);
    }

    @Override
    public void destroy() {
        try {
            communicationClient.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public String getMetricsName() {
        return METRICS_OUT;
    }

}
