package com.threadx.metrics.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 指标收集服务
 *
 * @author huangfukexing
 * @date 2023/4/12 15:52
 */
@SpringBootApplication
public class MetricsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetricsApplication.class, args);
    }
}
