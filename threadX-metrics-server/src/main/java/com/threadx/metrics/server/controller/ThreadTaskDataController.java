package com.threadx.metrics.server.controller;

import com.threadx.metrics.server.common.annotations.GlobalResultPackage;
import com.threadx.metrics.server.common.annotations.Login;
import com.threadx.metrics.server.service.ThreadTaskDataService;
import com.threadx.metrics.server.vo.ThreadTaskDataErrorTop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * *************************************************<br/>
 * 线程池任务控制器<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/6/11 0:01
 */
@RestController
@GlobalResultPackage
@Api(tags = "线程池任务控制器")
@RequestMapping("/threadTaskData")
public class ThreadTaskDataController {

    private final ThreadTaskDataService threadTaskDataService;

    public ThreadTaskDataController(ThreadTaskDataService threadTaskDataService) {
        this.threadTaskDataService = threadTaskDataService;
    }

    @Login
    @ApiOperation(value = "线程池任务错误数据top10")
    @GetMapping("findThreadTaskDataErrorCalculationTop10")
    public List<ThreadTaskDataErrorTop> findThreadTaskDataErrorCalculationTop10(){
        return threadTaskDataService.findThreadTaskDataErrorCalculation(10);
    }
}
