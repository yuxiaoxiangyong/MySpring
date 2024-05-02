package com.zhangying.myspring.beans.factory;

import java.lang.String;
import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.PropertyValue;
import com.zhangying.myspring.beans.PropertyValues;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.beans.factory.config.BeanFactoryPostProcessor;
import com.zhangying.myspring.beans.factory.config.BeanPostProcessor;
import com.zhangying.myspring.core.io.DefaultResourceLoader;
import com.zhangying.myspring.core.io.Resource;

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
                    // 替换value中的值
                    String strVal = ((String) value);
                    StringBuilder sb = new StringBuilder();
                    int sIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int eIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if(sIdx != -1 && eIdx != -1 && sIdx < eIdx){
                        sb.append(strVal.substring(sIdx + 2, eIdx));
                        String resVal = properties.getProperty(sb.toString());
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), resVal));
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
