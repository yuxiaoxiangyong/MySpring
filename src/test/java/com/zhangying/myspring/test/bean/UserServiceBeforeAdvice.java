package com.zhangying.myspring.test.bean;

import com.zhangying.myspring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @className: UserServiceBeforeAdvice
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 19:14
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法:" + method.getName());
    }
}
