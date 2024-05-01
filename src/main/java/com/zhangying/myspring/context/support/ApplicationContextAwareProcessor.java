package com.zhangying.myspring.context.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanPostProcessor;
import com.zhangying.myspring.context.ApplicationContext;
import com.zhangying.myspring.context.ApplicationContextAware;

/**
 * @className: ApplicationContextAwareProcessor
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 19:54
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
