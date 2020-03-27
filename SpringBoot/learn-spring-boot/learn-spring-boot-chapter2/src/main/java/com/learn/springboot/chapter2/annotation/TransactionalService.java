package com.learn.springboot.chapter2.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;


/**
 *
 * @author admin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Service
@Transactional
public @interface TransactionalService {
    @AliasFor(annotation = Service.class,attribute ="value")
    String name();
}
