package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @className: SimpleInstantiationStrategy
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 19:11
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 利用反射完成创建bean ----> JDK实例化
        Class<?> clazz = beanDefinition.getBean();
        try {
            if(null != ctor){
                // 带参构造
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
            else{
                // 无参构造
                return clazz.getDeclaredConstructor().newInstance();
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }
}
