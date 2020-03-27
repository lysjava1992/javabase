package com.learn.springboot.chapter2.annotation;

import java.lang.annotation.*;

/**
 *
 * @ClassName UserTag
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/22 10:13
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//jdk1.8
@Repeatable(UserTags.class)
public @interface UserTag {
    String value();
}
