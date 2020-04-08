package com.learn.oauth2.server4.config;

import com.learn.oauth2.server4.component.CustomTokenEnhancer;
import com.learn.oauth2.server4.service.CustomClientDetailsServiceImpl;
import com.learn.oauth2.server4.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.io.PipedReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-08 09:32
 **/
@Configuration
@EnableAuthorizationServer
public class CustomOauth2Config extends AuthorizationServerConfigurerAdapter{
      @Autowired
      private CustomUserDetailsServiceImpl userDetailsService;

      @Autowired
      private CustomClientDetailsServiceImpl clientDetailsService;

      @Autowired
      private AuthenticationManager authenticationManager;

      @Autowired
      private RedisConnectionFactory redisConnectionFactory;

      @Autowired
      private CustomTokenEnhancer customTokenEnhancer;
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
       security.allowFormAuthenticationForClients()
                //  /oauth/check_token端点开放
               .checkTokenAccess("isAuthenticated()")  //permitAll()
                // 能够验证和解析 token
                .checkTokenAccess("isAuthenticated()");
                // 能够访问我们的公钥
            //    .tokenKeyAccess("isAuthenticated()");
    }


    /**
     * 令牌授权与认证
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {


        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));

        endpoints.tokenStore(jwtTokenStore())
                .tokenStore(redisTokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> additionalInfo = new HashMap<>(2);
            additionalInfo.put("organization", authentication.getName());
            additionalInfo.put("key", "value");
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
    /**
     * 配置客户端详情
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.withClientDetails(clientDetailsService);
    }
    @Bean
    RedisTokenStore redisTokenStore(){
        RedisTokenStore tokenStore=new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

    @Bean
    JwtTokenStore jwtTokenStore(){
        JwtTokenStore jwtTokenStore= new JwtTokenStore(jwtAccessTokenConverter());
        return jwtTokenStore;
    }

    /**
     * 对称秘钥加密
     *      JwtAccessTokenConverter converter=new JwtAccessTokenConverter();
     *      converter.setSigningKey("duichenjiami");
     *      return converter;
     *  非对称加密
     * @return
     */
    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter= new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory=
                new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"), "123456".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("oauth2"));
     return converter;
    }
}
