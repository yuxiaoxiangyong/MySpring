package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @className: InstantiationStrategy
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 18:56
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args)throws BeansException;

}
