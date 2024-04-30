package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.core.io.Resource;
import com.zhangying.myspring.core.io.ResourceLoader;

/**
 * @className: BeanDefinitionReader
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 10:22
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;
    void loadBeanDefinitions(Resource... resources) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... location) throws BeansException;

}
