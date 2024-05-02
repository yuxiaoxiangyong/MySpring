package com.zhangying.myspring.beans.factory;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import javafx.beans.binding.ObjectExpression;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: BeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 14:51
 */
public interface BeanFactory {

    public Object getBean(String beanName) throws BeansException;

    public Object getBean(String beanName, Object... args) throws BeansException;

    <T> T getBean(String beanName, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;
}
