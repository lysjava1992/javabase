package com.learn.springboot.chapter12.annotation;

import com.learn.springboot.chapter12.valiadator.IsMobileValiadator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = IsMobileValiadator.class)
public @interface IsMobile {
    //true:参数必须有,进行格式校验      false:参数可以为空,不为空时也要进行进行格式校验
    boolean required() default true; //这个可有可无
    //提示信息
    String message() default "手机号码格式错误";//通过注解后输出的信息,可以自定义
    Class<?>[] groups() default{};
    Class<?extends Payload>[] payload() default{};

}
