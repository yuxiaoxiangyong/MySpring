package com.zhangying.myspring.test.event;

import com.zhangying.myspring.context.ApplicationListener;

import java.util.Date;

/**
 * @className: CustomEventListener
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 13:52
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
