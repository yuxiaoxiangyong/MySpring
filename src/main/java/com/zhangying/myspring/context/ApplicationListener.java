package com.zhangying.myspring.context;

import java.util.EventListener;

/**
 * @className: ApplicationListener
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 22:35
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);

}
