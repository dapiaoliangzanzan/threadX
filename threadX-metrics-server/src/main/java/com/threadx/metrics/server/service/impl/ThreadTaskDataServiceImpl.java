package com.threadx.metrics.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.entity.InstanceItem;
import com.threadx.metrics.server.entity.ThreadTaskData;
import com.threadx.metrics.server.mapper.ThreadTaskDataMapper;
import com.threadx.metrics.server.service.InstanceItemService;
import com.threadx.metrics.server.service.ThreadTaskDataService;
import com.threadx.metrics.server.vo.ThreadTaskDataErrorTop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final ThreadTaskDataMapper threadTaskDataMapper;
    private final InstanceItemService instanceItemService;

    public ThreadTaskDataServiceImpl(ThreadTaskDataMapper threadTaskDataMapper, InstanceItemService instanceItemService) {
        this.threadTaskDataMapper = threadTaskDataMapper;
        this.instanceItemService = instanceItemService;
    }

    @Override
    public List<ThreadTaskDataErrorTop> findThreadTaskDataErrorCalculation(int limit) {
        List<ThreadTaskDataErrorTop> threadTaskDataErrorCalculations = threadTaskDataMapper.findThreadTaskDataErrorCalculation(limit);
        if (CollUtil.isNotEmpty(threadTaskDataErrorCalculations)) {
            List<Long> instanceIdList = threadTaskDataErrorCalculations.stream().map(ThreadTaskDataErrorTop::getInstanceId).collect(Collectors.toList());
            List<InstanceItem> inIds = instanceItemService.findInIds(instanceIdList);
            Map<Long, String> instanceItemIndex = new HashMap<>();
            for (InstanceItem instanceItem : inIds) {
                instanceItemIndex.put(instanceItem.getId(), instanceItem.getInstanceName());
            }
            return threadTaskDataErrorCalculations.stream().map(threadTaskDataErrorCalculation -> {
                ThreadTaskDataErrorTop threadTaskDataErrorTop = new ThreadTaskDataErrorTop();
                BeanUtil.copyProperties(threadTaskDataErrorCalculation, threadTaskDataErrorTop);
                threadTaskDataErrorTop.setThreadPoolGroupName(instanceItemIndex.getOrDefault(threadTaskDataErrorCalculation.getInstanceId(), "未知服务"));
                return threadTaskDataErrorTop;
            }).collect(Collectors.toList());

        }
        return null;
    }

    @Override
    public void batchSave(Collection<ThreadTaskData> collection) {
        this.saveBatch(collection);
    }
}
