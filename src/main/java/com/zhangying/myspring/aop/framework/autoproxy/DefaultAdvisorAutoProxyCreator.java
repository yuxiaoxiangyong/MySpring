package com.zhangying.myspring.aop.framework.autoproxy;

import com.zhangying.myspring.aop.*;
import com.zhangying.myspring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.zhangying.myspring.aop.framework.ProxyFactory;
import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.BeanFactory;
import com.zhangying.myspring.beans.factory.BeanFactoryAware;
import com.zhangying.myspring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.zhangying.myspring.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @className: DefaultAdvisorAutoProxyCreator
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 17:17
 */
public abstract class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = ((DefaultListableBeanFactory) beanFactory);
    }


    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (isInfrastructureClass(beanClass)) return null;

        // 切面Bean （包含切点 + 通知（拦截方法））
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) continue; // 判断当前bean是否位于切点表达式的作用域

            AdvisedSupport advisedSupport = new AdvisedSupport();

            // 构造目标对象
            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice()); // advice需要自己定义？
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            // 生成并返回代理对象
            return new ProxyFactory(advisedSupport).getProxy();

        }

        return null;
    }


    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
