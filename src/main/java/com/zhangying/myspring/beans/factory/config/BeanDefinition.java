package com.zhangying.myspring.beans.factory.config;

/**
 * @className: BeanDefinition
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 14:51
 */
public class BeanDefinition {

    private Class bean;

    public BeanDefinition(Class bean) {
        this.bean = bean;
    }

    public Class getBean() {
        return bean;
    }

    public void setBean(Class bean) {
        this.bean = bean;
    }
}
