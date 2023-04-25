package com.threadx.metrics.server.service;

import com.threadx.metrics.server.entity.ThreadPoolData;

import java.util.Collection;

/**
 * *************************************************<br/>
 * 线程池参数业务服务<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/4/23 15:09
 */
public interface ThreadPoolDataService {

    /**
     * 批量保存
     * @param collection 需要批量保存的
     */
    void batchSave(Collection<ThreadPoolData> collection);
}
