package com.zhangying.myspring.util;

/**
 * 解析字符串操作的接口
 * @className: StringValueResolver
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 21:39
 */
public interface StringValueResolver {

    /**
     * 解析传入的字符串，并返回解析结果
     * @param strVal
     * @return
     */
    String resolveStringValue(String strVal);
}
