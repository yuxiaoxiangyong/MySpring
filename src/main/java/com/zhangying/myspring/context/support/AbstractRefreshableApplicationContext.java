package com.zhangying.myspring.context.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.ConfigurableListableBeanFactory;
import com.zhangying.myspring.beans.factory.support.DefaultListableBeanFactory;

/**
 * @className: AbstractRefreshableApplicationContext
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 19:43
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
