package com.zhangying.myspring.beans.factory;

/**
 * @className: DisposableBean
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 10:30
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
