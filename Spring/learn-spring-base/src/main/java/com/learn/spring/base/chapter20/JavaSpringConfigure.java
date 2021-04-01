package com.learn.spring.base.chapter20;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.DataBinder;

@Configuration
@ComponentScan("com.learn.spring.base.chapter20")
public class JavaSpringConfigure {
    @Bean
    public DataBinder binder(){
        DataBinder dataBinder=new DataBinder(new PersonValidator());
        return dataBinder;
    }
}
