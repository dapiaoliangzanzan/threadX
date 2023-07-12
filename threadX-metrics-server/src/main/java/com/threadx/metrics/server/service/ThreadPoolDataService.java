package com.threadx.metrics.server.service;

import com.threadx.metrics.server.conditions.ThreadPoolDetailConditions;
import com.threadx.metrics.server.conditions.ThreadPoolPageDataConditions;
import com.threadx.metrics.server.entity.ThreadPoolData;
import com.threadx.metrics.server.vo.*;

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
     *
     * @param collection 需要批量保存的
     */
    void batchSave(Collection<ThreadPoolData> collection);


    /**
     * 根据查询条件分页查询
     *
     * @param threadPoolPageDataConditions 查询条件
     * @return 分页信息
     */
    ThreadxPage<ThreadPoolDataVo> findPageByThreadPoolPageDataConditions(ThreadPoolPageDataConditions threadPoolPageDataConditions);

    /**
     * 查询线程池详情
     *
     * @param threadPoolDetailConditions 实例详情查询
     * @return 实例的详情
     */
    ThreadPoolDetailsVo findThreadPoolDetail(ThreadPoolDetailConditions threadPoolDetailConditions);

    /**
     * 查询线程池状态的数量  根据实例的id
     * @param instanceId 实例的id
     * @return 返回线程池的数量
     */
    InstanceStateCountVo findThreadPoolStateCountByInstanceId(Long instanceId);
}
