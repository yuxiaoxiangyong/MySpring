package com.zhangying.myspring.beans.factory.annotation;

import java.lang.annotation.*;

/*
 * Marks a constructor, field, setter method or config method as to be
 * autowired by Spring's dependency injection facilities.
 */
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    String value() default "";

}
