package com.zhangying.myspring.beans.factory.config;

/**
 * @className: BeanReference
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 21:03
 */
public class BeanReference {

    private String beanName;


    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
