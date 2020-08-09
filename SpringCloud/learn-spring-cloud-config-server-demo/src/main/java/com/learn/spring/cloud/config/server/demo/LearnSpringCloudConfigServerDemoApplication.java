package com.learn.spring.cloud.config.server.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class LearnSpringCloudConfigServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringCloudConfigServerDemoApplication.class, args);
	}

}
