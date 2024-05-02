package com.zhangying.myspring.beans.factory.config;

/**
 * @className: SingletonBeanRegistry
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:06
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    void registrySingleton(String beanName, Object singletonObject);
}
