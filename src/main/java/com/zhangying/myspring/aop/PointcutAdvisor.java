package com.zhangying.myspring.aop;

/**
 * @className: PointcutAdvisor
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 17:03
 */
public interface PointcutAdvisor extends Advisor{

    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();

}
