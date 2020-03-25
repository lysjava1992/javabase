package com.learn.spring.boot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-25 17:12
 **/
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnWebApplication
@Configuration
public class HelloServiceAutoConfiguration {
    @Autowired
    HelloProperties helloProperties;

    @Bean
    public HelloService helloService(){
        HelloService service=new HelloService();
        service.setHelloProperties(helloProperties);
        return service;
    }
}
