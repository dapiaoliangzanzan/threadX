package com.threadx.metrics.server.common.annotations;

import java.lang.annotation.*;

/**
 * 需要登录
 *
 * @author huangfukexing
 * @date 2023/6/1 07:51
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Login {
    /**
     * 是否需要登录
     * @return 返回访问的方法，是否需要登录！
     */
    boolean value() default true;
}
