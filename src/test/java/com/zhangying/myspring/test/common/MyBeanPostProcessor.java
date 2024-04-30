package com.zhangying.myspring.test.common;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanPostProcessor;
import com.zhangying.myspring.test.bean.UserService;

/**
 * @className: MyBeanPostProcessor
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 22:16
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
            UserService userService = ((UserService) bean);
            userService.setLocation("改为北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
