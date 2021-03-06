package com.hand.written.spring.formwork.annotation;

import java.lang.annotation.*;

/**
 * @author admin
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
