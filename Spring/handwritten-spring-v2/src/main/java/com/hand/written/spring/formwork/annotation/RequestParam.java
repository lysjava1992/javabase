package com.hand.written.spring.formwork.annotation;

import java.lang.annotation.*;

/**
 *
 * @author admin
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value()default "";
}
