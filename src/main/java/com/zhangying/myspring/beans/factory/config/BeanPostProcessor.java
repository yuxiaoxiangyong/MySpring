package com.zhangying.myspring.beans.factory.config;

import com.zhangying.myspring.beans.BeansException;

/**
 * @className: BeanPostProcessor
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 19:26
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
