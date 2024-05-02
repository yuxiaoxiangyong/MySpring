package com.zhangying.myspring.context.annotation;

import java.lang.annotation.*;

/**
 * @className: Scope
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 20:17
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
