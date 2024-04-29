package com.zhangying.myspring.beans;

/**
 * @className: BeanException
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:11
 */
public class BeansException extends Exception{

    public BeansException(){};

    public BeansException(String message){
        super(message);
    }
}
