package com.learn.springboot.chapter9.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-30 11:05
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 此处内置 用户名密码
     *    必须配置role;
     *    必须是加密后的密码
     *此处和配置文件，此处优先
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("张三").roles("admin").password("$2a$10$na0QmgR2fwRFGHWTSMK/BO7Gei8SKeYUv7b5V7Y/TIsPTMWe8Rvdi")
                .and()
                .withUser("admin").roles("user").password("$2a$10$jD655YyPIgdeHHGF.OV3NeVXEGF/diKNZrgot2.MPaPA6GidtqXGK");
    }
    /**
     *加密验证策略
     **/
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * 安全构建器
     *  主要配置不需要拦截的静态资源文件
     *  WebSecurity不仅通过HttpSecurity定义某些请求的安全控制，
     * 也通过其他方式定义其他某些请求可以忽略安全控制
     **/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/ok/**");
    }

    /**
     *  安全构建器
     *  HttpSecurity仅用于定义需要安全控制的请求
     *  (当然HttpSecurity也可以指定某些请求不需要安全控制);
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/admin/**").hasRole("admin")
               .antMatchers("/normal/**").authenticated()
               .and()
               .formLogin() //登录请求不验证
               .permitAll()
               .and()
               .httpBasic()
               .and()
               .csrf().disable();
    }


}
