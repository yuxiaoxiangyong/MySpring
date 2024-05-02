package com.zhangying.myspring.aop;

/**
 * 用于切点找到给定的接口和目标类
 * @className: ClassFilter
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 14:43
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);

}
