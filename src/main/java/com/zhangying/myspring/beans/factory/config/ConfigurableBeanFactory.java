package com.zhangying.myspring.beans.factory.config;

import com.zhangying.myspring.beans.factory.HierarchicalBeanFactory;
import com.zhangying.myspring.util.StringValueResolver;

/**
 * @className: ConfigurableBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 15:07
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    /**
     * Add a String resolver for embedded values such as annotation attributes.
     * @param valueResolver the String resolver to apply to embedded values
     * @since 3.0
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);
}
