package com.hand.written.spring.formwork.annotation;

import java.lang.annotation.*;

/**
 *
 * @author admin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value()default "";
}
