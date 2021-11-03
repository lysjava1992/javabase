package com.test.securtiy.component.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *  对认证信息的封装
 */
public class SmsLoginAuthenticationToken extends AbstractAuthenticationToken {

     private String phone;
     private String code;

    public SmsLoginAuthenticationToken(String phone,String code) {
        super(null);
        this.phone=phone;
        this.code=code;
        super.setAuthenticated(false);
    }
    public SmsLoginAuthenticationToken(String phone,String code,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.phone=phone;
        this.code=code;
        super.setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return code;
    }

    @Override
    public String getPrincipal() {
        return phone;
    }
}
