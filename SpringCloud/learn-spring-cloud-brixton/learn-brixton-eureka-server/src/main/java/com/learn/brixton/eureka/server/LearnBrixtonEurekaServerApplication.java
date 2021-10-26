package com.learn.brixton.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @EnableEurekaServer 开启注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class LearnBrixtonEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnBrixtonEurekaServerApplication.class, args);
	}

}
