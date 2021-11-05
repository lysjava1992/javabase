package com.learn.oauth2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 *  定义令牌端点上的安全约束
 *   认证服务器
 *   负责认证客户端
 *     发放令牌
 * @ClassName AuthorizationServiceCpnfig
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/4 19:13
 * @Version 1.0
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServiceConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许客户端以表单方式
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("permitAll()")
        .tokenKeyAccess("permitAll()");
    }

    /**
     * 用来配置客户端详情服务
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                //使用内存存储
                .inMemory()
                //指定客户端Id
                .withClient("client_id")
                //客户端安全码
                .secret("$2a$10$z7zPpUeOYk0UjQd5kATi8uuMgmeWwNrxAgqS4CUBNMPFhSM4uzoeW")
                 // 直接自动授权成功返回Code
                .autoApprove(true)
                // 重定向URL
                //授权模式
                .authorizedGrantTypes("password","refresh_token")
                //过期和刷新时间
                .accessTokenValiditySeconds(1800)
                //刷新token过期时间
                .refreshTokenValiditySeconds(60*60*2)
                //资源id
                .resourceIds("rid")
                //客户端范围
                .scopes("all","test");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) //基于密码模式，必须配置authenticationManager
                 .userDetailsService(userDetailsService); //密码模式必须配置

    }
}
