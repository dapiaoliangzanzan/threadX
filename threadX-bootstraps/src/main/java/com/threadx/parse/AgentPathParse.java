package com.threadx.parse;

import com.threadx.description.agent.AgentPackageDescription;
import com.threadx.utils.ThreadXCollectionUtils;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 插件路径解析
 *
 * @author huangfukexing
 * @date 2023/3/10 12:45
 */
public class AgentPathParse {


    static final String JAVA_AGENT_OPTION = "-javaagent:";
    public static final String JAVA_AGENT_NAME = "threadX-bootstraps";
    static final String AGENT_CONF_FILE_NAME = "threadX.properties";
    static final Logger logger = Logger.getGlobal();

    /**
     * 解析agent程序对应的目录信息
     *
     * @return 返回目录信息
     */
    public static AgentPackageDescription parse() throws Exception {
        Path agentPath = findAgentPath();
        Path agentParent = getAgentRootDir();
        Path libsDirPath = agentParent.resolve("libs");
        Path bootDirPath = agentParent.resolve("boot");
        Path confDirPath = agentParent.resolve("conf");
        Path dataDirPath = Paths.get(agentParent.toString(), "data");
        Path logsDirPath = Paths.get(agentParent.toString(), "logs");
        List<Path> orderLibPaths = findLibs(libsDirPath);
        List<URL> libsUrls = new ArrayList<>();
        if (ThreadXCollectionUtils.isNotEmpty(orderLibPaths)) {
            for (Path orderLibPath : orderLibPaths) {
                try {
                    logger.info("Search for lib dependencies: " + orderLibPath.toString());
                    URL url = orderLibPath.toUri().toURL();
                    libsUrls.add(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        //获取BootLib jar file
        List<File> bootLibJars = findBootLibJars();
        // 读取配置信息
        Properties envProperties = readEnvProperties(confDirPath);
        return new AgentPackageDescription(agentPath, libsDirPath, orderLibPaths, libsUrls, bootDirPath, bootLibJars, confDirPath, dataDirPath, logsDirPath, envProperties);
    }

    /**
     * 读取环境的配置信息
     *
     * @param confDirPath 配置目录
     * @return 配置信息
     */
    private static Properties readEnvProperties(Path confDirPath) throws Exception {
        if (confDirPath == null) {
            throw new RuntimeException("Error agent config path, not found threadX config dir: ${THREADX_HOME}/conf");
        }
        File confDirFile = confDirPath.toFile();
        File file = new File(confDirFile, AGENT_CONF_FILE_NAME);
        if (!file.exists()) {
            throw new RuntimeException("Error agent config data, not found threadX config file: ${THREADX_HOME}/conf/threadX.properties");
        }
        //读取配置信息
        Properties properties = new Properties();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            logger.info("Load ${THREADX_HOME}/conf/threadX.properties data.");
            properties.load(reader);
        }

        return properties;
    }


    /**
     * 获取插件的根目录
     *
     * @return 插件的根目录
     */
    private static Path getAgentRootDir() {
        Path agentPath = findAgentPath();
        if (agentPath == null) {
            throw new RuntimeException("The location of threadX-bootstraps-${version}.jar is not found. Add -javaagent: the absolute path to your threadX-agent.jar on the startup command.");
        }
        return agentPath.getParent();
    }


    /**
     * 寻找boot目录的jar文件
     *
     * @return 其中所有的jar
     */
    public static List<File> findBootLibJars() {
        Path agentRootDir = getAgentRootDir();
        Path bootDirPath = agentRootDir.resolve("boot");
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(bootDirPath, "*.jar")) {
            List<File> list = new ArrayList<>();
            for (Path path : paths) {
                if (path != null) {
                    list.add(path.toFile());
                }
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(bootDirPath + " Path IO error", e);
        }
    }

    /**
     * 查找依赖的集合
     *
     * @param libsDirPath 依赖目录
     * @return 依赖集合
     */
    private static List<Path> findLibs(Path libsDirPath) {
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(libsDirPath, "*.{jar,xml,properties}")) {
            List<Path> list = new ArrayList<>();
            for (Path path : paths) {
                list.add(path);
            }

            List<Path> orderList = new ArrayList<>();

            for (Path path : list) {
                Path fileName = path.getFileName();
                if (fileName != null && fileName.startsWith("threadX-")) {
                    orderList.add(path);
                }
            }

            for (Path path : list) {
                Path fileName = path.getFileName();
                if (fileName != null && !fileName.startsWith("threadX-")) {
                    orderList.add(path);
                }
            }
            return orderList;
        } catch (IOException e) {
            throw new RuntimeException(libsDirPath + " Path IO error", e);
        }
    }


    /**
     * 寻找插件的绝对路径信息
     *
     * @return 插件的绝对路径信息
     */
    private static Path findAgentPath() {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> inputArguments = runtimeMxBean.getInputArguments();
        for (String inputArgument : inputArguments) {
            String formatArgument = removeJavaagent(inputArgument);
            if (formatArgument.contains(JAVA_AGENT_NAME)) {
                return Paths.get(formatArgument);
            }
        }
        return null;

    }

    /**
     * 删除agent前缀
     *
     * @param argument 参数信息
     * @return 不带前缀的字符串
     */
    public static String removeJavaagent(String argument) {
        return argument.substring(JAVA_AGENT_OPTION.length());
    }
}
