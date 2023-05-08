package com.threadx.metrics.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * 指标收集服务
 *
 * @author huangfukexing
 * @date 2023/4/12 15:52
 */
@MapperScan("com.threadx.metrics.server.mapper")
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass = true)
public class MetricsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetricsApplication.class, args);
    }
}
