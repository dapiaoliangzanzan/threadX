package com.threadx.metrics.server.controller;

import com.threadx.metrics.server.common.annotations.GlobalResultPackage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * *************************************************<br/>
 * 指标数据接口<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/4/24 20:22
 */
@RestController
@GlobalResultPackage
@RequestMapping("/metrics")
public class MetricsController {

    @GetMapping("findServers")
    public List<String> findServers(){
        return Arrays.asList("test1","test2");
    }
}
