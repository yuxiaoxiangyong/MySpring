package com.zhangying.myspring.beans.factory;

/**
 * Callback that allows a bean to be aware of the bean{@link ClassLoader class loader};
 * that is, the class loader used by the present bean factory to load bean classes.
 * @className: BeanClassLoaderAware
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 19:49
 */
public interface BeanClassLoaderAware extends Aware{

    void setClassLoader(ClassLoader classLoader);

}
