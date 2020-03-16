package com.hand.written.spring.annotation;

import java.lang.annotation.*;

/**
 *
 * @author admin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowried {
    String value()default "";
}
