package com.test.securtiy.component;

import com.sun.org.apache.regexp.internal.RE;
import com.test.securtiy.component.ajax.AjaxLoginAuthenticationFailureHandler;
import com.test.securtiy.component.ajax.AjaxLoginAuthenticationProcessingFilter;
import com.test.securtiy.component.ajax.AjaxLoginAuthenticationProvider;
import com.test.securtiy.component.ajax.AjaxLoginAuthenticationSuccessHandler;
import com.test.securtiy.component.sms.SmsAuthenticationFailureHandler;
import com.test.securtiy.component.sms.SmsLoginAuthenticationProcessingFilter;
import com.test.securtiy.component.sms.SmsLoginAuthenticationProvider;
import com.test.securtiy.service.UserService;
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
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;

@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authenticationProvider());
        auth.authenticationProvider(ajaxLoginAuthenticationProvider());
        auth.authenticationProvider(smsLoginAuthenticationProvider());

    }

    private AuthenticationProvider ajaxLoginAuthenticationProvider() {
        AjaxLoginAuthenticationProvider provider = new AjaxLoginAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setSessionRegistry(sessionRegistry());
        return provider;
    }

    /**
     * 配置自定义 UserDetailsService
     *
     * @return
     */
    private AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(customUserDetailsService);
        return provider;
    }

    private AuthenticationProvider smsLoginAuthenticationProvider() {
        SmsLoginAuthenticationProvider provider = new SmsLoginAuthenticationProvider();
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public AjaxLoginAuthenticationProcessingFilter ajaxLoginAuthenticationProcessingFilter() throws Exception {
        AjaxLoginAuthenticationProcessingFilter filter = new AjaxLoginAuthenticationProcessingFilter(sessionRegistry());
        filter.setFilterProcessesUrl("/ajax/login");
        filter.setAuthenticationFailureHandler(new AjaxLoginAuthenticationFailureHandler());
        filter.setAuthenticationSuccessHandler(new AjaxLoginAuthenticationSuccessHandler());
        filter.setAuthenticationManager(authenticationManager());
        filter.setSessionAuthenticationStrategy(sessionControlAuthenticationStrategy());
        return filter;
    }

    public ConcurrentSessionControlAuthenticationStrategy sessionControlAuthenticationStrategy() {
        ConcurrentSessionControlAuthenticationStrategy strategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        strategy.setMaximumSessions(3);
        strategy.setExceptionIfMaximumExceeded(true);
        return strategy;
    }

    public SmsLoginAuthenticationProcessingFilter smsLoginAuthenticationProcessingFilter() throws Exception {
        SmsLoginAuthenticationProcessingFilter filter = new SmsLoginAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(new SmsAuthenticationFailureHandler());
        return filter;
    }
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new SimpleRedirectSessionInformationExpiredStrategy("/err");
    }
    /**
     * SpringSecurity 默认不支持明文
     * 必须配置密码编码器
     *
     * @return
     */
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/static/**", "/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //注册过滤器
        // 目标过滤器 UsernamePasswordAuthenticationFilter 用于配置顺序
        // 即添加的过滤器在UsernamePasswordAuthenticationFilter之前生效
        http.addFilterBefore(ajaxLoginAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(smsLoginAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/h2-console/**", "/ajax/login", "/sms/code").permitAll()
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
//        http.sessionManagement()
//                .maximumSessions(3)
//                .sessionRegistry(sessionRegistry());

        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(),sessionInformationExpiredStrategy()),ConcurrentSessionFilter.class);
        //    session并发控制过滤器
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/err")
                .sessionRegistry(sessionRegistry());
    }
}
