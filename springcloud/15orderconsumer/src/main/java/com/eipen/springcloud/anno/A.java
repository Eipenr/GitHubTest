package com.eipen.springcloud.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface A {
    //这个注解没有含义，就是说明在包扫描时不扫描加了这个注解的类
}
