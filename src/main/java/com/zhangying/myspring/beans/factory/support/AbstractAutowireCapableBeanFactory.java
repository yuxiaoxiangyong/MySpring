package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;

/**
 * @className: AbstractAutowireCapableBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:09
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {

        // 这里的处理逻辑是默认情况下实例化的bean都是单例的，指定模式下才会在每次请求时新创建bean
        Object bean = null;
        try {
            bean = beanDefinition.getBean().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
