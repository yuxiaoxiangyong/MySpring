package com.zhangying.myspring.beans.factory;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.AutowireCapableBeanFactory;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.beans.factory.config.ConfigurableBeanFactory;

/**
 *  * Configuration interface to be implemented by most listable bean factories.
 *  * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 *  * analyze and modify bean definitions, and to pre-instantiate singletons.
 * @className: ConfigurableListableBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 15:03
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
