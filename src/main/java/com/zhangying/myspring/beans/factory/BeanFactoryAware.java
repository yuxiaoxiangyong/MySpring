package com.zhangying.myspring.beans.factory;

import com.zhangying.myspring.beans.BeansException;

/**
 * Interface to be implemented by beans that wish to be aware of their owning{@link BeanFactory}.
 * @BeanFactory: 超级接口
 * @className: BeanFactoryAware
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 19:46
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory)throws BeansException;

}
