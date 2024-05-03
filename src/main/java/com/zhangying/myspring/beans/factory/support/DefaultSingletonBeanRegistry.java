package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.factory.DisposableBean;
import com.zhangying.myspring.beans.factory.ObjectFactory;
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

    protected static final Object NULL_OBJECT = new Object();

    // 单例bean的缓存  一级缓存  成品对象
    private final Map<String, Object> singletonObjects = new HashMap<>();

    // 二级缓存 半成品对象（只是实例化，并没有进行属性赋值）
    private final Map<String, Object> earlySingletonObjects = new HashMap<>();

    // 三级缓存 存放代理对象/工厂Bean
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    // 销毁方法缓存 key:beanName value:destroyAdapter
    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        //return singletonObjects.get(beanName);
        Object singletonObject = singletonObjects.get(beanName);
        if(null == singletonObject){
            singletonObject = earlySingletonObjects.get(beanName);
            if(null == singletonObject){
                ObjectFactory<?> objectFactory = singletonFactories.get(beanName);
                if(objectFactory != null){
                    try {
                        singletonObject = objectFactory.getObject();
                        earlySingletonObjects.put(beanName, singletonObject);
                        singletonFactories.remove(beanName);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }
        return singletonObject;
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


    @Override
    public void registrySingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }


    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        this.singletonFactories.put(beanName, singletonFactory);
    }

}
