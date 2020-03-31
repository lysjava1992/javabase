package com.learn.springboot.chapter10.component;

import com.learn.springboot.chapter10.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Security配置
 *
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-30 11:05
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SecuritySuccessHandler securitySuccessHandler;

    @Autowired
    SecurityFailedHandler securityFailedHandler;

//    @Autowired
//    VerifyCodeFilter verifyCodeFilter;

    @Autowired
    CustomUserDetailsService userDetailsService;
    /**
     * 此处内置用户名密码   必须配置role;必须是加密后的密码
     * 此处和配置文件，此处优先
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
           auth.userDetailsService(userDetailsService);
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
      // http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class)
        http      .authorizeRequests()
                .antMatchers("/code").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .successHandler(securitySuccessHandler)
                .failureHandler(securityFailedHandler)
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }

}