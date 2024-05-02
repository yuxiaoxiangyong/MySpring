package com.zhangying.myspring.aop.framework;

/**
 * @className: AopProxy
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 15:17
 */
public interface AopProxy {

    /**
     * 获取代理类，具体代理类的实现有JDK && CGlib
     * @return
     */
    Object getProxy();
}
