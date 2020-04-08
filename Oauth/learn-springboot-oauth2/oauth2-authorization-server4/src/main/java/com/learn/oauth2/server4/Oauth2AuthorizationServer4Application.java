package com.learn.oauth2.server4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author admin
 */
@MapperScan("com.learn.oauth2.server4.dao")
@SpringBootApplication
public class Oauth2AuthorizationServer4Application {
	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthorizationServer4Application.class, args);
	}
}