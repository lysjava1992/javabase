package com.hand.written.spring.formwork.annotation;

import java.lang.annotation.*;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Autowired
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 9:48
 * @Version 1.0
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String  value() default "";
}
