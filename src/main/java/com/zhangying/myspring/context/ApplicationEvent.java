package com.zhangying.myspring.context;

import java.util.EventObject;

/**
 * @className: ApplicationEvent
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 22:27
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
