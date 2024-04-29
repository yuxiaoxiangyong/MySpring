package com.zhangying.myspring.beans.factory.config;

import com.zhangying.myspring.beans.PropertyValues;

/**
 * @className: BeanDefinition
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 14:51
 */
public class BeanDefinition {

    private Class bean;

    private PropertyValues propertyValues;

    public BeanDefinition(Class bean, PropertyValues propertyValues) {
        this.bean = bean;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }

    public BeanDefinition(Class bean) {
        this.bean = bean;
        this.propertyValues = new PropertyValues();
    }

    public Class getBean() {
        return bean;
    }

    public void setBean(Class bean) {
        this.bean = bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
