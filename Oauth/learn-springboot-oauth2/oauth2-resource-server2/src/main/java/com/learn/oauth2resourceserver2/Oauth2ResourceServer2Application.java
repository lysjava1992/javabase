package com.learn.oauth2resourceserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author admin
 */
@EnableResourceServer
@SpringBootApplication
public class Oauth2ResourceServer2Application {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2ResourceServer2Application.class, args);
	}

}
