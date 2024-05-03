package com.zhangying.myspring.beans.factory;

import com.zhangying.myspring.beans.BeansException;

/**
 * @className: ObjectFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/3 13:16
 */
public interface ObjectFactory <T>{

    T getObject()throws BeansException;
}
