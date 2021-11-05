package com.learn.oauth2.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("admin").password("$2a$10$z7zPpUeOYk0UjQd5kATi8uuMgmeWwNrxAgqS4CUBNMPFhSM4uzoeW").roles("admin");
    }
     @Bean
     PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
      }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll();
       http.authorizeRequests()
               .antMatchers("/oauth/**").permitAll()
               .anyRequest().authenticated();
    }
}
