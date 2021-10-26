package com.learn.brixton.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LearnBrixtonConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnBrixtonConfigClientApplication.class, args);
	}

}
