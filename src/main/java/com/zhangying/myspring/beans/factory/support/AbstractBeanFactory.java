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
    public Object getBean(String beanName) {

        // 单例bean
        Object bean = getSingleton(beanName);
        if(bean != null){
            return bean;
        }

        // 单例不存在时，创建Bean
        try {
            BeanDefinition beanDefinition = getBeanDefination(beanName);
            bean = createBean(beanName, beanDefinition);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return bean;

    }

    protected abstract BeanDefinition getBeanDefination(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
    
}
