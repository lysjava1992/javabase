package com.hand.written.spring.formwork.annotation;

import java.lang.annotation.*;

/**
 *
 * @author admin
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value()default "";
}
