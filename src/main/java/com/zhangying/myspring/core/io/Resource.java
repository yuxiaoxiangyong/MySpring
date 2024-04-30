package com.zhangying.myspring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @className: Resource
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 22:05
 */
public interface Resource {

    InputStream getInputStream()throws IOException;

}
