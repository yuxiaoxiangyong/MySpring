package com.zhangying.myspring.test.event;

import com.zhangying.myspring.context.ApplicationEvent;

/**
 * @className: CustomEvent
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 13:51
 */
public class CustomEvent extends ApplicationEvent {

    private Long id;
    private String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
