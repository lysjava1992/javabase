package com.learn.oauth.server3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author admin
 */
@MapperScan("com.learn.oauth.server3.dao")
@SpringBootApplication
public class Oauth2AuthorizationServer3Application {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthorizationServer3Application.class, args);
	}

}
