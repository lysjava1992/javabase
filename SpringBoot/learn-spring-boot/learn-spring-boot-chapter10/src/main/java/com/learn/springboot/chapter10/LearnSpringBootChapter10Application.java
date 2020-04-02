package com.learn.springboot.chapter10;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@MapperScan("com.learn.springboot.chapter10.dao")
@SpringBootApplication
public class LearnSpringBootChapter10Application {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringBootChapter10Application.class, args);
	}

}
