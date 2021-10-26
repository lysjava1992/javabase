package com.learn.brixton.feign.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @EnableDiscoveryClient 服务发现
 * @EnableFeignClients 服务调用
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class LearnBrixtonFeignCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnBrixtonFeignCustomerApplication.class, args);
	}

}
