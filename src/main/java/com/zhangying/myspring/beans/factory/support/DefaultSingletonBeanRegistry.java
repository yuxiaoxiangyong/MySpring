package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: DefaultSingletonBeanRegistry
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:11
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 单例bean的缓存
    private final Map<String, Object> singletonObjects = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return null;
    }

    protected void addSingleton(String beanName, Object singletonObject){
        singletonObjects.get(beanName);
    }
}
