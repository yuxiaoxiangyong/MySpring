package com.zhangying.myspring.beans.factory.config;

import com.zhangying.myspring.beans.factory.HierarchicalBeanFactory;

/**
 * @className: ConfigurableBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 15:07
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
