package com.zhangying.myspring.beans.factory.config;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.ConfigurableListableBeanFactory;

/**
 * @className: BeanFactoryPostProcessor
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 19:25
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
