package com.zhangying.myspring.context.event;

import com.zhangying.myspring.beans.factory.BeanFactory;
import com.zhangying.myspring.context.ApplicationEvent;
import com.zhangying.myspring.context.ApplicationListener;

/**
 * 事件广播器的顶级实现（包含全部功能）
 * Simple implementation of the {@link ApplicationEventMulticaster} interface.
 * @className: SimpleApplicationEventMulticaster
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 10:30
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{


    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }


    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
