package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.factory.config.BeanDefinition;

/**
 * @className: BeanDefinationRegistry
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:09
 */
public interface BeanDefinitionRegistry {
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    public boolean containsBeanDefinition(String beanName);
}
