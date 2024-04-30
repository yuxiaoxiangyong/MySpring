package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.ConfigurableListableBeanFactory;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: DefaultListableBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:10
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }


    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }


    @Override
    protected BeanDefinition getBeanDefination(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null)throw new BeansException();
        return beanDefinition;
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null)throw new BeansException();
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        for(String key : beanDefinitionMap.keySet()){
            getBean(key); // 实例化bean
        }
        //beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> res = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefiniton)->{
            // 在已经注册的BeanDefiniton中寻找FactoryPostProcessor
            Class beanClass = beanDefiniton.getBean();
            if(type.isAssignableFrom(beanClass)){
                // 判断当前类是否为type或其子类
                try {
                    res.put(beanName, ((T) getBean(beanName)));
                } catch (BeansException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return res;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
