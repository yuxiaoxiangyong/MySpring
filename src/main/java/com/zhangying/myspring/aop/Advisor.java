package com.zhangying.myspring.aop;

import org.aopalliance.aop.Advice;

/**
 * @className: Advisor
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 17:02
 */
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();

}
