package com.zhangying.myspring.beans.factory;

import com.zhangying.myspring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: BeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 14:51
 */
public interface BeanFactory {

    public Object getBean(String beanName);

}
