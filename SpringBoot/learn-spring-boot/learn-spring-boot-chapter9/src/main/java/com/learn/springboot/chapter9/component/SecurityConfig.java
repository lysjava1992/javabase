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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("张三").roles("admin").password("$2a$10$na0QmgR2fwRFGHWTSMK/BO7Gei8SKeYUv7b5V7Y/TIsPTMWe8Rvdi")
                .and()
                .withUser("admin").roles("user").password("$2a$10$jD655YyPIgdeHHGF.OV3NeVXEGF/diKNZrgot2.MPaPA6GidtqXGK");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/ok/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/admin/**").hasRole("admin")
               .antMatchers("/normal/**").authenticated()
               .and()
               .formLogin()
               .permitAll()
               .and()
               .httpBasic()
               .and()
               .csrf().disable();
    }


}
