package com.zhangying.myspring.test;

import com.zhangying.myspring.aop.AdvisedSupport;
import com.zhangying.myspring.aop.TargetSource;
import com.zhangying.myspring.aop.aspectj.AspectJExpressionPointcut;
import com.zhangying.myspring.aop.framework.Cglib2AopProxy;
import com.zhangying.myspring.aop.framework.JdkDynamicAopProxy;
import com.zhangying.myspring.context.support.ClassPathXmlApplicationContext;
import com.zhangying.myspring.test.bean.*;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @className: TestAoP
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 14:59
 */
public class TestAoP {

    @Test
    public void TestPointCutExpression() throws NoSuchMethodException, ClassNotFoundException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.zhangying.myspring.test.bean.UserService.*(..))");
        Class<?> clazz = Class.forName("com.zhangying.myspring.test.bean.UserService");
        Method method = clazz.getDeclaredMethod("queryUserInfo");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }


    @Test
    public void TestDynamic(){

        // 目标对象
        // 动态代理要求 目标对象和代理对象至少有一个公共接口，目前切入点根据接口得到的
        // 底层会判断当前方法 与目标方法是否匹配，匹配的才会进行动态代理，否则直接调用反射方法
        MyUserService userService = new MyUserService();

        // 组装信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.zhangying.myspring.test.bean.IUserService.*(..))"));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());

        // JDK动态代理
        IUserService userService1 = ((IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy());

        // 测试调用
        //userService1.queryUserInfo();

        // Cglib动态代理
        IUserService userService2 = ((IUserService) new Cglib2AopProxy(advisedSupport).getProxy());
        userService2.queryUserInfo();
    }


    @Test
    public void test_property() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        AutoBeanUserService userService = applicationContext.getBean("autoBeanUserService", AutoBeanUserService.class);
        System.out.println("测试结果：" + userService);
    }


    @Test
    public void test_scan() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        AutoBeanUserService userService = applicationContext.getBean("autoBeanUserService", AutoBeanUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
