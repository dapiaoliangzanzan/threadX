package com.threadx.check.impl;

import com.threadx.check.ThreadPoolExecutorInterceptCheck;
import com.threadx.utils.ThreadXThreadPoolUtil;

/**
 * 是不是需要被拦截的包前缀
 *
 * @author huangfukexing
 * @date 2023/3/14 14:27
 */
public class IncludeTokenThreadPoolExecutorInterceptCheck extends ThreadPoolExecutorInterceptCheck {

    @Override
    public boolean interceptCheck() {
        String threadPoolName = ThreadXThreadPoolUtil.generateThreadPoolGroupName();
        return threadPoolName.contains("huangfu");
    }
}
