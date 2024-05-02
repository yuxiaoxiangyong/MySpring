package com.zhangying.myspring.context.event;

/**
 * @className: ContextRefreshedEvent
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 22:30
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
