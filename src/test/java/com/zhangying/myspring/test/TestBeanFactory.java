package com.zhangying.myspring.test;

import com.zhangying.myspring.BeanDefinition;
import com.zhangying.myspring.BeanFactory;
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
    public void TestCreateBeanFactory() {

        // 定义Bean容器
        BeanFactory beanFactory = new BeanFactory();

        // 创建Bean
        UserService userService = new UserService();

        //
        beanFactory.registerBeanDefination("userService", new BeanDefinition(userService));
        ((UserService) beanFactory.getBean("userService")).queryUserInfo();
    }



}
