package com.test.securtiy.component;

import com.sun.org.apache.regexp.internal.RE;
import com.test.securtiy.component.ajax.AjaxLoginAuthenticationFailureHandler;
import com.test.securtiy.component.ajax.AjaxLoginAuthenticationProcessingFilter;
import com.test.securtiy.component.ajax.AjaxLoginAuthenticationProvider;
import com.test.securtiy.component.ajax.AjaxLoginAuthenticationSuccessHandler;
import com.test.securtiy.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.authenticationProvider(ajaxLoginAuthenticationProvider());
    }

    private AuthenticationProvider ajaxLoginAuthenticationProvider() {
        AjaxLoginAuthenticationProvider provider=new AjaxLoginAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     *  配置自定义 UserDetailsService
     *
     * @return
     */
    private AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(customUserDetailsService);
        return provider;
    }

    @Bean
    public AjaxLoginAuthenticationProcessingFilter ajaxLoginAuthenticationProcessingFilter() throws Exception {
        AjaxLoginAuthenticationProcessingFilter filter = new AjaxLoginAuthenticationProcessingFilter();
        filter.setFilterProcessesUrl("/ajax/login");
        filter.setAuthenticationFailureHandler(new AjaxLoginAuthenticationFailureHandler());
        filter.setAuthenticationSuccessHandler(new AjaxLoginAuthenticationSuccessHandler());
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    /**
     *  SpringSecurity 默认不支持明文
     *  必须配置密码编码器
     * @return
     */
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/static/**","/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //注册原理
        http.addFilterBefore(ajaxLoginAuthenticationProcessingFilter(),AbstractPreAuthenticatedProcessingFilter.class);

        http.authorizeRequests()
                .antMatchers("/h2-console/**","/ajax/login").permitAll()
                .anyRequest().authenticated()
                .and()

                 // 表单登录
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth/login")
                .successForwardUrl("/index")
                .failureHandler(new CustomAuthenticationFailureHandler())
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .deleteCookies()

                .and()
                .httpBasic()
                .and()
                // 开启H2内存数据库控制台
                .csrf().disable()
                .headers().frameOptions().sameOrigin();
    }
}
