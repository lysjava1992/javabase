package com.learn.spring.base.chapter24;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Propagation;

@Configuration
@ComponentScan("com.learn.spring.base.chapter24")
@EnableAspectJAutoProxy
public class Chapter24Config {
}
