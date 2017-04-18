package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于类、接口、注解类型、方法
 * <p>
 * Created by li.huan
 * Create Date 2017-04-18 11:37
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeAnnotation {
    //使用枚举类型
    LoginType type() default LoginType.Common;
}
