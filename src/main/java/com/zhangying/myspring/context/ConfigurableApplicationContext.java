package com.zhangying.myspring.context;

import com.zhangying.myspring.beans.BeansException;

/**
 * @className: ConfigurableApplicationContext
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 19:29
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
