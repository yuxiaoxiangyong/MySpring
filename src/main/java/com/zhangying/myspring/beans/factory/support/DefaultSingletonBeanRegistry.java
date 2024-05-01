package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.DisposableBean;
import com.zhangying.myspring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @className: DefaultSingletonBeanRegistry
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:11
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 单例bean的缓存
    private final Map<String, Object> singletonObjects = new HashMap<>();


    // 销毁方法缓存 key:beanName value:destroyAdapter
    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject){
        //singletonObjects.get(beanName);
        singletonObjects.put(beanName, singletonObject);
    }


    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                System.out.println(("Destroy method on bean with name '" + beanName + "' threw an exception"));
            }
        }
    }

}
