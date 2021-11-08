package com.learn.oauth2.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableResourceServer
@Configuration
public class Oauth2ResourcesServer extends ResourceServerConfigurerAdapter {
    /**
     * RemoteTokenServices 负责远程调用检查token
     * 当认证和资源管理服务器在一起时 不用配置
     * 是两个模块时配置
     *  资源服务器需要
     * @return
     */
//    @Primary
//    @Bean
//    public RemoteTokenServices tokenServices() {
//        final RemoteTokenServices tokenService = new RemoteTokenServices();
//        //配置认证服务器地址
//        // 资源管理器需要通过授权管理服务器来验证toke
//        // 此时对授权管理服务器来说资源管理器也是一个客户端
//        tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//        tokenService.setClientId("cid");
//        tokenService.setClientSecret("123456");
//        return tokenService;
//    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //配置资源ID
        resources.resourceId("rid")
                 .tokenStore(jwtTokenStore());
    }

    //配置资源权限
    //使用默认的配置，表示对所有资源都需要授权认证，即授权通过后可以访问所有资源
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
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

}
