package com.zhangying.myspring.test.common;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.PropertyValue;
import com.zhangying.myspring.beans.PropertyValues;
import com.zhangying.myspring.beans.factory.ConfigurableListableBeanFactory;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @className: MyBeanFactoryProcessor
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 22:14
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为字节跳动"));
    }

}
