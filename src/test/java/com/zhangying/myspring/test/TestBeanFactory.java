package com.zhangying.myspring.test;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.beans.factory.BeanFactory;
import com.zhangying.myspring.beans.factory.support.DefaultListableBeanFactory;
import com.zhangying.myspring.test.bean.UserService;
import org.junit.Test;

/**
 * @className: TestBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 15:21
 */
public class TestBeanFactory {

    @Test
    public void TestCreateBeanFactory() throws Exception{
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }


    @Test
    public void TestContructorBeanFactory() throws Exception{
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        UserService userService = null;
        userService = (UserService) beanFactory.getBean("userService", "小傅哥");

        userService.queryUserInfo();
    }


}
