package com.zhangying.myspring.beans.factory;

/**
 * @className: InitializingBean
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 10:30
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;

}
