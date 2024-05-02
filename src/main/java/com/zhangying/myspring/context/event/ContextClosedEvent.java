package com.zhangying.myspring.context.event;

/**
 * @className: ContextClosedEvent
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 22:30
 */
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }

}
