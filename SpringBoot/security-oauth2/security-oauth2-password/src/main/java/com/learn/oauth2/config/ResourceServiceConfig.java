package com.learn.oauth2.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *   资源服务器
 * @ClassName ResourceServiceConfig
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/5 14:04
 * @Version 1.0
 **/
@Configuration
@EnableResourceServer
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {
    /**
     * RemoteTokenServices 负责远程调用检查token
     * 当认证和资源管理服务器在一起时 不用配置
     * 是两个模块时配置
     * @return
     */
//    @Primary
//    @Bean
//    public RemoteTokenServices tokenServices() {
//        final RemoteTokenServices tokenService = new RemoteTokenServices();
//        //配置认证服务器地址
//        tokenService.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token");
//        tokenService.setClientId("client_id");
//        tokenService.setClientSecret("123456");
//        return tokenService;
//    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //配置资源ID
        resources.resourceId("rid");
    }

    //配置资源权限
    //使用默认的配置，表示对所有资源都需要授权认证，即授权通过后可以访问所有资源
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/user/**").hasRole("user")
//                .anyRequest().authenticated();
    }
}




