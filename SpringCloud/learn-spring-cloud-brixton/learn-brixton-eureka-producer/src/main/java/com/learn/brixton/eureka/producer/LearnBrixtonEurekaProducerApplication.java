package com.learn.brixton.eureka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @EnableEurekaClient 声明为客户端需要注册
 */
@EnableEurekaClient
@SpringBootApplication
public class LearnBrixtonEurekaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnBrixtonEurekaProducerApplication.class, args);
	}

}
