package com.zhangying.myspring.context;

import com.zhangying.myspring.beans.factory.HierarchicalBeanFactory;
import com.zhangying.myspring.beans.factory.ListableBeanFactory;
import com.zhangying.myspring.core.io.Resource;
import com.zhangying.myspring.core.io.ResourceLoader;

/**
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 * @className: ApplicationContext
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 19:27
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {



}
