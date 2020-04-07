package com.learn.oauth2.server2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author admin
 *  授权服务器
 *     客户端的验证与授权
 *     令牌的生成与发放
 *     令牌的校验与更新
 */
@EnableAuthorizationServer
@SpringBootApplication
public class Oauth2AuthorizationServer2Application {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthorizationServer2Application.class, args);
	}

}
