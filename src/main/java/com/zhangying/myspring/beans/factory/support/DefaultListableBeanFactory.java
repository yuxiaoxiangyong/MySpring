package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: DefaultListableBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:10
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefination(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null)throw new BeansException();
        return beanDefinition;
    }
}
