package com.learn.brixton.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @SpringCloudApplication 组合注解：
 *             —— @SpringBootApplication     //启动
 *             —— @EnableDiscoveryClient     //注册
 *             —— @EnableCircuitBreaker     //熔断
 * @EnableZuulProxy 网关代理
 */
@EnableZuulProxy
@SpringCloudApplication
public class LearnBrixtonZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnBrixtonZuulApplication.class, args);
	}

}
