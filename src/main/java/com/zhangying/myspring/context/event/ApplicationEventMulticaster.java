package com.zhangying.myspring.context.event;

import com.zhangying.myspring.context.ApplicationEvent;
import com.zhangying.myspring.context.ApplicationListener;

/**
 * @className: ApplicationEventMulticaster
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 22:31
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);

}
