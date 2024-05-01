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

    private String initMethodName; // 初始化方法名

    private String destroyMethodName; // 销毁方法名

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


    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }


    public String getInitMethodName() {
        return initMethodName;
    }


    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }


    public String getDestroyMethodName() {
        return destroyMethodName;
    }


    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
}
