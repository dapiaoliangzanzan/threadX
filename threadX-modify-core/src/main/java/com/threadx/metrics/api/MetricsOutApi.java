package com.threadx.metrics.api;

import java.util.List;

/**
 * 指标数据输出API
 *
 * @author huangfukexing
 * @date 2023/3/24 20:33
 */
public interface MetricsOutApi {

    /**
     * 初始化
     */
    void init();

    /**
     * 输出单条指标数据
     *
     * @param metricsData 指标数据
     */
    void outMetricsData(String metricsData);


    /**
     * 输出多条指标数据
     *
     * @param metricsDataList 指标数据列表
     */
    void outMetricsData(List<String> metricsDataList);

    /**
     * 销毁对象
     */
    void destroy();

    /**
     * 获取指标名称
     *
     * @return 返回指标名称
     */
    String getMetricsName();
}
