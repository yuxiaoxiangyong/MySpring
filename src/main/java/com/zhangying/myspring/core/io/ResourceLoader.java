package com.zhangying.myspring.core.io;

/**
 * @className: ResourceLoader
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 10:05
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
