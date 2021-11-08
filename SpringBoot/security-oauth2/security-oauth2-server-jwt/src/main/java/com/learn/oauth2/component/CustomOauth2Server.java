package com.learn.oauth2.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
public class CustomOauth2Server extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
          security.allowFormAuthenticationForClients();
          security.checkTokenAccess("permitAll()")
                  .tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
             clients.inMemory()
                     .withClient("cid")
                     .secret("$2a$10$z7zPpUeOYk0UjQd5kATi8uuMgmeWwNrxAgqS4CUBNMPFhSM4uzoeW")
                     .authorizedGrantTypes("client_credentials")
                     .accessTokenValiditySeconds(18000)
                     .resourceIds("rid")
                     .scopes("all");
    }
    @Bean
    public TokenStore jwtTokenStore() {
        // 最基本的InMemoryTokenStore生成token
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter=new JwtAccessTokenConverter();
        converter.setSigningKey("test-secret");
        return converter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
        .tokenStore(jwtTokenStore())
        .accessTokenConverter(jwtAccessTokenConverter());
    }
}
