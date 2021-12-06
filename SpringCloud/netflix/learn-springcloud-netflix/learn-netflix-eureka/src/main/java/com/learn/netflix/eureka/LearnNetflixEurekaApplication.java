package com.learn.netflix.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 *  @EnableEurekaServer 开启注册中心即可
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class LearnNetflixEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnNetflixEurekaApplication.class,args);
    }
}
