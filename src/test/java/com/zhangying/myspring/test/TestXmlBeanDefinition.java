package com.zhangying.myspring.test;

import com.zhangying.myspring.beans.factory.support.DefaultListableBeanFactory;
import com.zhangying.myspring.beans.factory.xml.XmlBeanDefinitionReader;
import com.zhangying.myspring.context.support.ClassPathXmlApplicationContext;
import com.zhangying.myspring.test.bean.UserService;
import com.zhangying.myspring.test.common.MyBeanPostProcessor;
import org.junit.Test;

/**
 * @className: TestXmlBeanDefinition
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 16:15
 */
public class TestXmlBeanDefinition {

    @Test
    public void TestXmlBeanInit() throws Exception{

        // 1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.从xml实例化Bean
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }


    @Test
    public void test_xml() throws Exception{
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:myspring.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo("10001");
    }
}