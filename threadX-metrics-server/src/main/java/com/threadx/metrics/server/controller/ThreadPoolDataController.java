package com.threadx.metrics.server.controller;

import com.threadx.metrics.server.common.annotations.GlobalResultPackage;
import com.threadx.metrics.server.common.annotations.Login;
import com.threadx.metrics.server.conditions.ThreadPoolDetailConditions;
import com.threadx.metrics.server.service.ThreadPoolDataService;
import com.threadx.metrics.server.vo.ThreadPoolDetailPackVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * *************************************************<br/>
 * 线程池数据控制器<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/7/1 20:38
 */
@RestController
@GlobalResultPackage
@Api(tags = "线程池数据控制器")
@RequestMapping("/threadPool")
public class ThreadPoolDataController {

    private final ThreadPoolDataService threadPoolDataService;

    public ThreadPoolDataController(ThreadPoolDataService threadPoolDataService) {
        this.threadPoolDataService = threadPoolDataService;
    }

    @Login
    @ApiOperation(value = "查询实例详情")
    @PostMapping("findThreadPoolDetail")
    public ThreadPoolDetailPackVo findThreadPoolDetail(@RequestBody ThreadPoolDetailConditions threadPoolDetailConditions) {
        return threadPoolDataService.findThreadPoolDetail(threadPoolDetailConditions);
    }
}
