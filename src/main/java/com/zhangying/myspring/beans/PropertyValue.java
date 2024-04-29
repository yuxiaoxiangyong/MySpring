package com.zhangying.myspring.beans;

/**
 * @className: PropertyValue
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 20:44
 */
public class PropertyValue {

    private final String name;

    private final Object value;


    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }


    public Object getValue() {
        return value;
    }

}
