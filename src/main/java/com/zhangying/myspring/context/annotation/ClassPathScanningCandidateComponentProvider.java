package com.zhangying.myspring.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @className: ClassPathScanningCandidateComponentProvider
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 20:22
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;

    }
}
