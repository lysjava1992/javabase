package com.learn.brixton.ribbon.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableDiscoveryClient 服务发现
 * @EnableCircuitBreaker 开启熔断器功能
 *
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class LearnBrixtonRibbonCustomerApplication {

	/**
	 * @LoadBalanced 开启负载均衡
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnBrixtonRibbonCustomerApplication.class, args);
	}

}
