package com.threadx;

import com.threadx.description.agent.AgentPackageDescription;
import com.threadx.description.context.AgentContext;
import com.threadx.parse.AgentPathParse;
import com.threadx.utils.ThreadXCollectionUtils;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.List;
import java.util.jar.JarFile;
import java.util.logging.Logger;

/**
 * @author huangfukexing
 * @date 2023/3/9 10:28
 */
public class ThreadxAgentBootstraps {
    static final Logger logger = Logger.getGlobal();

    public static void premain(String agentArg, Instrumentation inst) {

        logger.info("ThreadX.BootStraps agentArgs: " + agentArg);
        logger.info("ThreadX.ClassLoader: " + ThreadxAgentBootstraps.class.getClassLoader());
        logger.info("App.ContextClassLoader: " + Thread.currentThread().getContextClassLoader());
        //正常情况下 ThreadPoolExecutorAgent的加载需要 Boot类加载器加载，否则会出现ClassNotDef异常
        if (Object.class.getClassLoader() != ThreadxAgentBootstraps.class.getClassLoader()) {
            logger.info("Invalid threadX-bootstraps.jar:" + agentArg);
            return;
        }

        try {
            AgentPackageDescription agentPackageDescription = AgentPathParse.parse();
            AgentContext agentContext = new AgentContext(inst, agentArg, agentPackageDescription);
            registerBootLib(agentContext);
            registerClassLoader(agentContext);
            start(agentContext);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private static void registerBootLib(AgentContext agentContext) throws IOException {
        Instrumentation instrumentation = agentContext.getInstrumentation();
        AgentPackageDescription agentPackageDescription = agentContext.getAgentPackageDescription();
        List<File> bootJarFiles = agentPackageDescription.getBootJarFiles();
        if(ThreadXCollectionUtils.isNotEmpty(bootJarFiles)) {
            for (File bootJarFile : bootJarFiles) {
                logger.info("add boot jar:" + bootJarFile.getAbsolutePath());
                instrumentation.appendToBootstrapClassLoaderSearch(new JarFile(bootJarFile));
            }
        }
    }

    private static void start(AgentContext agentContext) throws Exception {
        ClassLoader classLoader = agentContext.getAgentClassLoader();
        if(classLoader != null) {
            String runIngClassName = getBootStrapClassName(agentContext);
            Class<?> bootClass = classLoader.loadClass(runIngClassName);
            final Thread currentThread = Thread.currentThread();
            final ClassLoader before = currentThread.getContextClassLoader();
            ModifyApplication modifyApplication;
            try {
                currentThread.setContextClassLoader(classLoader);
                Constructor<?> constructor = bootClass.getDeclaredConstructor(AgentContext.class);
                modifyApplication = (ModifyApplication) constructor.newInstance(agentContext);
            }finally {
                currentThread.setContextClassLoader(before);
            }
            modifyApplication.start();
        }
    }

    private static String getBootStrapClassName(AgentContext agentContext) {
        return "com.threadx.handler.AsmModifyApplicationHandler";
    }

    /**
     * 注册依赖 形成自定义类加载器
     *
     * @param agentContext 上下上
     */
    public static void registerClassLoader(AgentContext agentContext) {
        AgentPackageDescription agentPackageDescription = agentContext.getAgentPackageDescription();
        List<URL> libsUrls = agentPackageDescription.getLibsUrls();

        if (ThreadXCollectionUtils.isNotEmpty(libsUrls)) {
            URL[] libUrls = libsUrls.toArray(new URL[0]);
            AgentClassLoader agentClassLoader = new AgentClassLoader(libUrls, ThreadxAgentBootstraps.class.getClassLoader());
            agentContext.setAgentClassLoader(agentClassLoader);
        }
    }

}
