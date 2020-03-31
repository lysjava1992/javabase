package com.learn.springboot.chapter10.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-31 13:36
 **/
@Data
public class Customer implements UserDetails{
    private Long id;
    private String username;
    private String password;
    private String createTime;
    private String addr;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
