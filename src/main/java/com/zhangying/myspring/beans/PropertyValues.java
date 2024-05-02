package com.zhangying.myspring.beans;

import java.util.List;
import java.util.ArrayList;

/**
 * @className: PropertyValues
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 20:44
 */
public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();


    public void addPropertyValue(PropertyValue pv){
        propertyValueList.add(pv);
    }


    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }


    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }


    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }
}
