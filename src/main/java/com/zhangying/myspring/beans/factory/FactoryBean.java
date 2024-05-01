package com.zhangying.myspring.beans.factory;

/**
 * @className: FactoryBean
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 21:04
 */
public interface FactoryBean <T>{

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
