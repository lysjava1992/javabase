package com.learn.springboot.chapter10.component.sms;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @description:    在帐号密码登录的过程中，
 *  密码的正确性以及帐号是否可用是通过DaoAuthenticationProvider来校验的。
 *  手机登录同样需要
 * @author: Mr.Luan
 * @create: 2020-04-03 13:43
 **/
public class MobileAuthenticationProvider  implements AuthenticationProvider{

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         MobileAuthenticationToken authenticationToken= (MobileAuthenticationToken) authentication;
        UserDetails userDetails=userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (userDetails == null) {
            throw new UsernameNotFoundException("手机号无效");
        } else if (!userDetails.isEnabled()) {

            throw new DisabledException("用户已被禁用");

        } else if (!userDetails.isAccountNonExpired()) {

            throw new AccountExpiredException("账号已过期");

        } else if (!userDetails.isAccountNonLocked()) {

            throw new LockedException("账号已被锁定");

        } else if (!userDetails.isCredentialsNonExpired()) {

            throw new LockedException("凭证已过期");
        }

            MobileAuthenticationToken mobileAuthenticationToken=new
                    MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
            mobileAuthenticationToken.setDetails(authenticationToken.getDetails());
        return mobileAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MobileAuthenticationToken.class.isAssignableFrom(aClass);
    }
    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }



}
