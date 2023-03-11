package com.threadx.handler;

import com.threadx.ModifyApplication;
import com.threadx.description.context.AgentContext;
import com.threadx.transformer.ModifyThreadPoolExecutorTransformer;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * asm修改器
 *
 * @author huangfukexing
 * @date 2023/3/10 18:12
 */
public class AsmModifyApplicationHandler extends ModifyApplication {

    @Override
    public void start() throws Exception {
        Instrumentation instrumentation = AgentContext.getInstrumentation();
        instrumentation.addTransformer(new ModifyThreadPoolExecutorTransformer(), true);
        instrumentation.retransformClasses(ThreadPoolExecutor.class);
    }
}
