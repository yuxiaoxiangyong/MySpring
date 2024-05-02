package com.zhangying.myspring.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.zhangying.myspring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.beans.factory.support.BeanDefinitionRegistry;
import com.zhangying.myspring.stereotype.Component;

import java.util.Set;

/**
 * @className: ClassPathBeanDefinitonScanner
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 20:28
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{

    private BeanDefinitionRegistry registry;


    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    /**
     * 通过反射Annotation解析Bean的scope
     * @param beanDefinition
     * @return
     */
    private String resolveBeanScope(BeanDefinition beanDefinition){
        // 获取Bean的class
        Class<?> clazz = beanDefinition.getBean();
        Scope scope = clazz.getAnnotation(Scope.class);
        String name = "";
        if(null == scope)name = StrUtil.EMPTY;
        else name = scope.value();
        return name;
    }


    /**
     * 反射得到BeanName，默认为类名首字母小写
     * @param beanDefinition
     * @return
     */
    private String determineBeanName(BeanDefinition beanDefinition){
        // 获取Bean的class
        Class<?> clazz = beanDefinition.getBean();
        String name = clazz.getDeclaredAnnotation(Component.class).value();
        if(StrUtil.isEmpty(name))name = StrUtil.lowerFirst(clazz.getSimpleName());
        return name;
    }


    /**
     * 扫描指定包下加注解@Component的Bean
     * @param basePackages
     */
    public void doScan(String... basePackages){
        for(String basePackage : basePackages){
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for(BeanDefinition beanDefinition : candidates){
                String scope = resolveBeanScope(beanDefinition);
                if(StrUtil.isNotEmpty(scope))beanDefinition.setScope(scope);
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }

        // 注册处理注解的 BeanPostProcessor（@Autowired、@Value）
        // AutowiredAnnotationBeanPostProcessor并没有标注@Component,所以是无法在类扫描时注入到beanFactory中的,此处需要我们手动进行注册.
        registry.registerBeanDefinition("com.zhangying.myspring.context.annotation.internalAutowiredAnnotationProcessor", new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

}
