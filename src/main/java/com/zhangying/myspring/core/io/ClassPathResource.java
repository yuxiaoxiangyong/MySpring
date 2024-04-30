package com.zhangying.myspring.core.io;

import cn.hutool.core.util.ClassUtil;
import com.zhangying.myspring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @className: ClassPathResource
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 22:05
 */
public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path){
        this(path, ((ClassLoader) null));
    }


    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }


    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(
                    this.path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
