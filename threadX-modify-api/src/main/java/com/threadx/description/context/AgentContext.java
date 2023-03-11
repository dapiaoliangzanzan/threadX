package com.threadx.description.context;

import com.threadx.description.agent.AgentPackageDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.instrument.Instrumentation;

/**
 * 应用上下文
 *
 * @author huangfukexing
 * @date 2023/3/10 18:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentContext {

    /**
     * 使用的classLoad
     */
    private ClassLoader agentClassLoader;

    /**
     * 使用的instrumentation API
     */
    private Instrumentation instrumentation;

    /**
     * 应用参数
     */
    private String args;

    /**
     * agent包描述对象
     */
    private AgentPackageDescription agentPackageDescription;

    public AgentContext(Instrumentation instrumentation, String args, AgentPackageDescription agentPackageDescription) {
        this.instrumentation = instrumentation;
        this.args = args;
        this.agentPackageDescription = agentPackageDescription;
    }
}
