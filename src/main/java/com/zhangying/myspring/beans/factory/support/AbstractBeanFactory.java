package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.BeanFactory;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;

/**
 * @AbstractBeanFactory: 模板抽象类
 * @className: AbstractBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:09
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String beanName) throws BeansException{
        return doGet(beanName, null);
    }


    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGet(beanName, args);
    }


    protected <T> T doGet(String beanName, Object[] args) throws BeansException{
        Object bean = getSingleton(beanName);
        if(bean != null)return (T)bean;

        BeanDefinition beanDefinition = getBeanDefination(beanName);
        return (T)createBean(beanName, beanDefinition, args);
    }


    protected abstract BeanDefinition getBeanDefination(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
    
}
