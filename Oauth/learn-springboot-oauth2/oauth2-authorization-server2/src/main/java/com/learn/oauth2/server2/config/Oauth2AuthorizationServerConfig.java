package com.learn.oauth2.server2.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import java.time.Duration;

/**
 * @description:
 *  java config 模式下 可以自定义添加多个客户端
 * @RequiredArgsConstructor  Lomok 无参构造器
 * @author: Mr.Luan
 * @create: 2020-04-07 10:23
 **/
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private  AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        InMemoryClientDetailsServiceBuilder builder=clients.inMemory();
        builder  //构建客户端 oauth2  oauth2
                .withClient("oauth2")
                .secret("$2a$10$HtVNoM1uG0xigsKPtn5QZuU0B6aVC9w4i.XAZ1X/7x9M/OfcTQ7zi")
                //可访问资源ID
                .resourceIds("resource_id")
                //授权方式 密码 授权码  token可刷新
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                //可授权的角色
                .authorities("ROLE_USER","ROLE_ADMIN")
                //授权范围
                .scopes("all")
                //token 有效期
               .accessTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
                //刷新token 有效期
              .refreshTokenValiditySeconds(Math.toIntExact(Duration.ofDays(1).getSeconds()))
                //授权码模式的重定向地址
              .redirectUris("http://example.com");
        builder
                .withClient("oauth2_1")
                .secret("$2a$10$HtVNoM1uG0xigsKPtn5QZuU0B6aVC9w4i.XAZ1X/7x9M/OfcTQ7zi")
                .resourceIds("resource_id")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .authorities("ROLE_USER","ROLE_ADMIN")
                .scopes("test")
                .accessTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
                .refreshTokenValiditySeconds(Math.toIntExact(Duration.ofDays(1).getSeconds()))
                .redirectUris("http://example.com");
    }




    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
    }
}













