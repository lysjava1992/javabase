package com.test.securtiy.component.ajax;

import com.test.securtiy.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public class AjaxLoginAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailService;
    private PasswordEncoder passwordEncoder;
    private SessionRegistry sessionRegistry;


    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailService = userDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();// 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials();// 这个是表单中输入的密码；

        // 这里构建来判断用户是否存在和密码是否正确
        CustomUser userDetails = (CustomUser) userDetailService.loadUserByUsername(userName);

        if (userDetails == null) {
            throw new UsernameNotFoundException("无效用户");
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException("用户已被禁用");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("账号已被锁定");
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期");
        }else if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("密码错误");
        }
        AjaxLoginAuthenticationToken result = new AjaxLoginAuthenticationToken(userDetails,
                authentication.getCredentials(), userDetails.getAuthorities());
        result.setDetails(authentication.getDetails());
        // 构建返回的用户登录成功的token
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        /**
         * providerManager会遍历所有
         * securityconfig中注册的provider集合
         * 根据此方法返回true或false来决定由哪个provider
         * 去校验请求过来的authentication
         */
        return (AjaxLoginAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
