package com.zhangying.myspring.beans.factory;

import java.lang.String;
import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.PropertyValue;
import com.zhangying.myspring.beans.PropertyValues;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.beans.factory.config.BeanFactoryPostProcessor;
import com.zhangying.myspring.core.io.DefaultResourceLoader;
import com.zhangying.myspring.core.io.Resource;
import com.zhangying.myspring.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * 工程中通常会将配置信息存储到.properties文件中，然后使用占位符的方式读取到xml文件中
 * 在实例化Bean之前，更改BeanDefinition的属性值，将占位符替换为配置值
 * 具体应当在BeanFactoryProcessor中执行
 * @className: PropertyPlaceholderconfigurer
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 19:54
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        try {
            // 加载.properties文件
            DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
            Resource resource = defaultResourceLoader.getResource(location);
            Properties properties = new Properties(); // 加载properties文件
            properties.load(resource.getInputStream());

            // 加载BeanDefinitons
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for(String beanDefintionName : beanDefinitionNames){
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefintionName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();//List
                for(PropertyValue propertyValue : propertyValues.getPropertyValues()){
                    Object value = propertyValue.getValue();
                    if(!(value instanceof String))continue;
                    // 替换value中的值  集合并发修改异常  转array修改即可
                    value = resolvePlaceholder(((String) value), properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
                }

            }
            // 向容器中添加字符串解析器，供解析@Value注解使用
            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver); // 记录resolver实际记录的是properties文件

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }

    }


    /**
     * 根据属性配置文件，解析字符串
     * @param value
     * @param properties
     * @return
     */
    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();
    }
}
