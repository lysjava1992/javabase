package com.learn.springboot.chapter10.component;

import com.learn.springboot.chapter10.component.sms.MobileAuthenticationFilter;
import com.learn.springboot.chapter10.component.sms.MobileAuthenticationProvider;
import com.learn.springboot.chapter10.component.sms.MoblieSecurityConfig;
import com.learn.springboot.chapter10.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


/**
 * Security配置
 *
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-30 11:05
 **/
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true,jsr250Enabled=true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SecuritySuccessHandler securitySuccessHandler;
    @Autowired
    SecurityFailedHandler securityFailedHandler;

    @Autowired
    VerifyCodeFilter verifyCodeFilter;
    @Autowired
    MoblieCodeFilter moblieCodeFilter;

     @Autowired
    MoblieSecurityConfig moblieSecurityConfig;

    @Autowired
    CustomUserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        //取消默认模式，不然UserNotFoundExceptions不会抛出
        authProvider.setHideUserNotFoundExceptions(false);
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    /**
     * 加密验证策略
     **/
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 静态资源配置
     **/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/resource/**");
    }


    /**
     * 安全构建器
     * HttpSecurity仅用于定义需要安全控制的请求
     * (当然HttpSecurity也可以指定某些请求不需要安全控制);
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http .authorizeRequests()
                .antMatchers("/code","/mobile_code").permitAll()
                .anyRequest().authenticated()
                .and()
             //   .addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .successHandler(securitySuccessHandler)
                .failureHandler(securityFailedHandler)
                .permitAll()
                .and()
                .addFilterBefore(moblieCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .apply(moblieSecurityConfig)
                .and()
                .httpBasic()
                .and()
                .rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60*60*24*7)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .csrf().disable();
                 http.sessionManagement()
                .maximumSessions(3).expiredUrl("/login")
                .sessionRegistry(sessionRegistry());


    }



    @Bean
    public SessionRegistry sessionRegistry(){
        SessionRegistry sessionRegistry=new SessionRegistryImpl();
        return sessionRegistry;
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        PersistentTokenRepository tokenRepository=new InMemoryTokenRepositoryImpl();

        return tokenRepository;
    }
}