package com.zhangying.myspring.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: FactoryBeanRegistrySupport
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 21:05
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name --> object
     */
    private final Map<String, Object> factoryBeanObjectCache = new HashMap<String, Object>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != null ? object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : null));
            }
            return (object != null ? object : null);
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeanException(("FactoryBean threw exception on object[" + beanName + "] creation"));
        }

    }

}
