package com.hjx.springbootjwtdemo.annotction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
注解：用于标记一个方法，无需通过令牌
Controller中使用，拦截器中判断
*/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean reqired() default  true;

}

