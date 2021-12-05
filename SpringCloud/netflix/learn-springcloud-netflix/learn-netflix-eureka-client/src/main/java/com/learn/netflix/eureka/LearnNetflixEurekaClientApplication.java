package com.learn.netflix.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 客户端
 */
@EnableEurekaClient
@SpringBootApplication
public class LearnNetflixEurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnNetflixEurekaClientApplication.class,args);
    }
}
