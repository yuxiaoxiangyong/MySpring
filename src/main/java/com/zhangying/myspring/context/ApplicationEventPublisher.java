package com.zhangying.myspring.context;

/**
 * @className: ApplicationEventPublisher
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 22:43
 */
public interface ApplicationEventPublisher {

    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);

}
