package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.core.io.DefaultResourceLoader;
import com.zhangying.myspring.core.io.ResourceLoader;

/**
 * @className: AbstractBeanDefinitionReader
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 10:34
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}