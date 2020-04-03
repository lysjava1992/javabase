package com.learn.springboot.chapter10.component.sms;

import com.learn.springboot.chapter10.component.MoblieCodeFilter;
import com.learn.springboot.chapter10.component.SecurityFailedHandler;
import com.learn.springboot.chapter10.component.SecuritySuccessHandler;
import com.learn.springboot.chapter10.component.VerifyCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-03 15:22
 **/
@Component
public class MoblieSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


   @Autowired
   private UserDetailsService userDetailsService;
    @Autowired
    SecuritySuccessHandler securitySuccessHandler;
    @Autowired
    SecurityFailedHandler securityFailedHandler;

@Override
public void configure(HttpSecurity http) throws Exception {

        MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
    mobileAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    mobileAuthenticationFilter.setAuthenticationSuccessHandler(securitySuccessHandler);
    mobileAuthenticationFilter.setAuthenticationFailureHandler(securityFailedHandler);

        // 获取验证码提供者
        MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider();
        mobileAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(mobileAuthenticationProvider)
        .addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        }
}
