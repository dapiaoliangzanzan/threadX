package com.threadx.metrics.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.threadx.metrics.server.entity.ThreadPoolData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * *************************************************<br/>
 * 线程池数据mapper<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/4/23 15:07
 */
@Repository
public interface ThreadPoolDataMapper extends BaseMapper<ThreadPoolData> {


    /**
     * 根据条件查询id最大的那条记录
     *
     * @param threadPoolName 线程池的名称
     * @param instanceId     实例的id
     * @return 线程池的数据
     */
    ThreadPoolData findByMaxIdAndThreadPoolNameAndInstanceId(@Param("threadPoolName") String threadPoolName, @Param("instanceId") Long instanceId);

}
