package com.zhangying.myspring.test.bean;

import com.zhangying.myspring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @className: SpouseAdvice
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/3 15:21
 */
public class SpouseAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("关怀小两口(切面)：" + method);
    }

}