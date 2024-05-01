package com.zhangying.myspring.beans.factory;

/**
 * Interface to be implemented by beans that want to be aware of their bean name in a bean factory.
 * @className: BeanNameAware
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 19:50
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
