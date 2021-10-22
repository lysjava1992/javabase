package com.learn.springboot.chapter10.component.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @description:  自定义短信认证令牌
 *                  与UsernamePasswordAuthenticationToken相比
 *                  把与密码有关的配置去掉/忽略即可
 * @author: Mr.Luan
 * @create: 2020-04-03 13:19
 **/
public class MobileAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 520L;
    private final Object principal;
    private Object credentials;

    public MobileAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        this.setAuthenticated(false);
    }

    public MobileAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }


    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

}
