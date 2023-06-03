package com.threadx.metrics.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threadx.metrics.server.entity.ActiveLog;
import com.threadx.metrics.server.mapper.ActiveLogMapper;
import com.threadx.metrics.server.service.ActiveLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * *************************************************<br/>
 * <br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/6/3 23:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActiveLogServiceImpl extends ServiceImpl<ActiveLogMapper, ActiveLog> implements ActiveLogService {
    @Override
    public void saveLog(ActiveLog activeLog) {
        if(activeLog != null) {
            save(activeLog);
        }
    }

    @Override
    public void batchSave(List<ActiveLog> activeLogs) {
        if (CollUtil.isNotEmpty(activeLogs)) {
            saveBatch(activeLogs);
        }
    }
}
