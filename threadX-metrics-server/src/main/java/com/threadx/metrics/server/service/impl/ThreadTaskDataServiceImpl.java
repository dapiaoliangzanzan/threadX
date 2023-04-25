package com.threadx.metrics.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.entity.ThreadPoolData;
import com.threadx.metrics.server.entity.ThreadTaskData;
import com.threadx.metrics.server.mapper.ThreadPoolDataMapper;
import com.threadx.metrics.server.mapper.ThreadTaskDataMapper;
import com.threadx.metrics.server.service.ThreadPoolDataService;
import com.threadx.metrics.server.service.ThreadTaskDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * *************************************************<br/>
 * 线程池数据操作业务类<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/4/23 15:09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ThreadTaskDataServiceImpl extends ServiceImpl<ThreadTaskDataMapper, ThreadTaskData> implements ThreadTaskDataService {

    @Override
    public void batchSave(Collection<ThreadTaskData> collection) {
        this.saveBatch(collection);
    }
}
