package com.threadx.log;

import com.threadx.description.agent.AgentPackageDescription;
import com.threadx.description.context.AgentContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.spi.LoggerContext;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * threadX日志工厂，日志全局配置
 *
 * @author huangfukexing
 * @date 2023/3/11 19:43
 */
public class LoggerSlf4jLog4j2 extends ThreadXLoggerFactoryApi {
    private final static AtomicBoolean IS_LOAD = new AtomicBoolean(false);


    /**
     * 获取一个类加载器
     *
     * @param targetClass 目标Class
     * @return 返回对应的日志信息
     */
    @Override
    public Logger getLogger(Class<?> targetClass) {

        try {
            if (IS_LOAD.compareAndSet(false, true)) {
                registerLogConfig();
            }
            org.apache.logging.log4j.Logger logger = LogManager.getLogger(targetClass);
            return new Slf4jLoggerProxy(logger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 注册日志的配置文件
     */
    private void registerLogConfig() {
        AgentPackageDescription agentPackageDescription = AgentContext.getAgentPackageDescription();
        Path confDirPath = agentPackageDescription.getConfDirPath();
        File logDirPath = confDirPath.toFile();
        File logConfigFile = new File(logDirPath, "log4j2.xml");
        Configurator.initialize("ThreadX-LogConfig", logConfigFile.getAbsolutePath());
    }
}
