package com.zhangying.myspring.context.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.support.DefaultListableBeanFactory;
import com.zhangying.myspring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @className: AbstractXmlApplicationContext
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 19:45
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory){
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            try {
                beanDefinitionReader.loadBeanDefinitions(configLocations);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract String[] getConfigLocations();

}
