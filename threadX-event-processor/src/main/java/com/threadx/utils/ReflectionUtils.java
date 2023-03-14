package com.threadx.utils;

import java.lang.reflect.Constructor;

/**
 * 反射工具类
 *
 * @author huangfukexing
 * @date 2023/3/14 15:22
 */
public class ReflectionUtils {

    /**
     * 根据构造函数创建对象
     *
     * @param targetClass 目标类型
     * @param paramTypes  参数类型
     * @param params      参数信息
     * @param <T>         目标的类型
     * @return 创建的对象
     * @throws Exception 创建失败
     */
    public static <T> T newInstanceConstructor(Class<T> targetClass, Class<?>[] paramTypes, Object[] params) throws Exception {
        if (paramTypes != null && paramTypes.length > 0) {
            Constructor<T> declaredConstructor = targetClass.getDeclaredConstructor(paramTypes);
            return declaredConstructor.newInstance(params);
        } else {
            return targetClass.newInstance();
        }
    }

    /**
     * 根据构造函数创建对象  空构造函数
     *
     * @param targetClass 目标类型
     * @param <T>         目标的类型
     * @return 创建的对象
     * @throws Exception 创建失败
     */
    public static <T> T newInstanceConstructor(Class<T> targetClass) throws Exception {
        return ReflectionUtils.newInstanceConstructor(targetClass, null, null);
    }
}
