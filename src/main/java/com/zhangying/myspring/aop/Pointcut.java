package com.zhangying.myspring.aop;

/**
 * @className: Pointcut
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 14:42
 */
public interface Pointcut {

    /**
     * Return the ClassFilter for this pointcut.
     * @return the ClassFilter (never <code>null</code>)
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     * @return the MethodMatcher (never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();

}
