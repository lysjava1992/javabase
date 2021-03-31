package com.learn.spring.base.chapter17;

import com.learn.spring.base.chapter17.service.HelloService;
import com.learn.spring.base.chapter17.service.HelloServiceImpl2;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.support.ResourceBundleMessageSource;

@ComponentScan("com.learn.spring.base.chapter17")
@Configuration
public class SpringConfigurationRootClass {


    @Bean("messageSource")
    public ResourceBundleMessageSource resourceBundleMessageSource(){
        ResourceBundleMessageSource resource=new ResourceBundleMessageSource();
        resource.setBasename("message");
        resource.setDefaultEncoding("utf-8");
        resource.setUseCodeAsDefaultMessage(true);
        return  resource;
    }

    @Bean("helloService3")
    public HelloService helloService3(){
        return new HelloServiceImpl2();
    }
}
