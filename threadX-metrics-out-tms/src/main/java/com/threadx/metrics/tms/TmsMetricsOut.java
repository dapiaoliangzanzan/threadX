package com.threadx.metrics.tms;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.threadx.communication.client.CommunicationClient;
import com.threadx.communication.client.config.ClientConfig;
import com.threadx.communication.common.agreement.packet.ThreadPoolCollectMessage;
import com.threadx.communication.common.agreement.packet.ThreadPoolTaskCollectMessage;
import com.threadx.description.context.AgentContext;
import com.threadx.log.Logger;
import com.threadx.log.ThreadXLoggerFactoryApi;
import com.threadx.log.factory.ThreadXAgetySystemLoggerFactory;
import com.threadx.metrics.ThreadPoolExecutorData;
import com.threadx.metrics.ThreadTaskExecutorData;
import com.threadx.metrics.api.MetricsOutApi;
import com.threadx.metrics.tms.config.ThreadXMetricsTmsPropertiesEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ************************************************<br/>
 * threadX 指标收集服务类型<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/4/9 14:55
 */
public class TmsMetricsOut implements MetricsOutApi {

    private final static Logger logger = ThreadXAgetySystemLoggerFactory.getLogger(TmsMetricsOut.class);
    private final static AtomicInteger COUNT = new AtomicInteger(1);

    public static final String METRICS_OUT = "tms";
    private final List<CommunicationClient> communicationClientList = new ArrayList<>();

    @Override
    public void init() {
        String addressStr = AgentContext.getAgentPackageDescription().getEnvProperties().getProperty(ThreadXMetricsTmsPropertiesEnum.THREADX_METRICS_OUT_TMS_ADDRESS.getKey(), ThreadXMetricsTmsPropertiesEnum.THREADX_METRICS_OUT_TMS_ADDRESS.getDefaultValue());
        if (ThreadXMetricsTmsPropertiesEnum.THREADX_METRICS_OUT_TMS_ADDRESS.getDefaultValue().equalsIgnoreCase(addressStr)) {
            logger.error("Indicator collector initialization failed. Please check the configuration: {}", ThreadXMetricsTmsPropertiesEnum.THREADX_METRICS_OUT_TMS_ADDRESS.getKey());
            throw new RuntimeException("Indicator collector initialization failed. Please check the configuration.");
        }

        //先分隔所有的节点
        String[] remoteNodeAddressArray = addressStr.split(",");
        for (String remoteNodeAddress : remoteNodeAddressArray) {
            String[] address = remoteNodeAddress.split(":");
            String host = address[0];
            String port = address[1];
            CommunicationClient communicationClient = new CommunicationClient(new ClientConfig(host, Integer.parseInt(port), AgentContext.getServerName(), AgentContext.getInstanceName()));
            communicationClientList.add(communicationClient);
        }
    }

    @Override
    public void outThreadPoolMetricsData(ThreadPoolExecutorData metricsData) {
        ThreadPoolCollectMessage threadPoolCollectMessage = new ThreadPoolCollectMessage();
        BeanUtil.copyProperties(metricsData, threadPoolCollectMessage);
        communicationSelector().asyncSendMessage(threadPoolCollectMessage);
    }

    @Override
    public void outThreadTaskMetricsData(ThreadTaskExecutorData metricsData) {
        ThreadPoolTaskCollectMessage threadPoolTaskCollectMessage = new ThreadPoolTaskCollectMessage();
        BeanUtil.copyProperties(metricsData, threadPoolTaskCollectMessage);
        communicationSelector().asyncSendMessage(threadPoolTaskCollectMessage);
    }

    /**
     * 通讯选择器
     *
     * @return 通讯客户端
     */
    public CommunicationClient communicationSelector() {
        int select =  COUNT.getAndIncrement() % communicationClientList.size();
        return communicationClientList.get(select);
    }

    @Override
    public void destroy() {
        for (CommunicationClient communicationClient : communicationClientList) {
            try {
                communicationClient.close();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

    }

    @Override
    public String getMetricsName() {
        return METRICS_OUT;
    }

}
