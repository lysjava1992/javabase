package com.learn.oauth2.server2.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.time.Duration;

/**
 * @description:  配置文件下多个 客户端的配置
 *  java config 模式下 可以自定义添加多个客户端
 * @RequiredArgsConstructor  Lomok 无参构造器
 * @author: Mr.Luan
 * @create: 2020-04-07 10:23
 **/
//@Configuration
//@RequiredArgsConstructor
//@EnableAuthorizationServer
public class Oauth2AuthorizationServerConfig2 extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ClientDetails clientDetails;

    /**
     *  此处封装配置文件 到
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder=clients.inMemory();
        for (BaseClientDetails bcd:clientDetails.getClient()) {
            ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder clientBuilder
                     =builder.withClient(bcd.getClientId());
            clientBuilder
                    .secret(bcd.getClientSecret())
                    .resourceIds(bcd.getResourceIds().toArray(new String[0]))
                    .authorizedGrantTypes(bcd.getAuthorizedGrantTypes().toArray(new String[0]))
                    .authorities(
                            AuthorityUtils.authorityListToSet(bcd.getAuthorities())
                                          .toArray(new String[0]))
                    .scopes(bcd.getScope().toArray(new String[0]));
              if(bcd.getAutoApproveScopes()!=null){
                    clientBuilder.accessTokenValiditySeconds(bcd.getAccessTokenValiditySeconds());
              }
            if (bcd.getRefreshTokenValiditySeconds() != null) {
                clientBuilder.refreshTokenValiditySeconds(
                        bcd.getRefreshTokenValiditySeconds());
            }
            if (bcd.getRegisteredRedirectUri() != null) {
                clientBuilder.redirectUris(
                        bcd.getRegisteredRedirectUri().toArray(new String[0]));
            }
        }
    }




    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
    }
}













