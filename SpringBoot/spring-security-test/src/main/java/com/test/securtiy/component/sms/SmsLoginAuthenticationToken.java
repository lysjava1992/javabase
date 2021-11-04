package com.test.securtiy.component.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *  对认证信息的封装
 */
public class SmsLoginAuthenticationToken extends AbstractAuthenticationToken {

     private Object phone;
     private Object code;

    public SmsLoginAuthenticationToken(Object phone,Object code) {
        super(null);
        this.phone=phone;
        this.code=code;
        super.setAuthenticated(false);
    }
    public SmsLoginAuthenticationToken(Object phone,Object code,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.phone=phone;
        this.code=code;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return code;
    }

    @Override
    public Object  getPrincipal() {
        return phone;
    }
}
