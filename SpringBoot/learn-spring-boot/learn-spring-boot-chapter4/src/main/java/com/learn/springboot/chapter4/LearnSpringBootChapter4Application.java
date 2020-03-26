package com.learn.springboot.chapter4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LearnSpringBootChapter4Application {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringBootChapter4Application.class, args);
	}

}
