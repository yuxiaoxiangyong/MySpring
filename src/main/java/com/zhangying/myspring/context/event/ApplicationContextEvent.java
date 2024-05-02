package com.zhangying.myspring.context.event;

import com.zhangying.myspring.context.ApplicationContext;
import com.zhangying.myspring.context.ApplicationEvent;

/**
 * @className: ApplicationContextEvent
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 22:28
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }


    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
