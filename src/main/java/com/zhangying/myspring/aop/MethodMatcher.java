package com.zhangying.myspring.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配，找到表达式范围内匹配下的目标类和方法
 * @className: MethodMatcher
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 14:43
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);

}
