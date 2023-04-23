package com.threadx.metrics.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.threadx.metrics.server.entity.ThreadPoolData;
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
}
