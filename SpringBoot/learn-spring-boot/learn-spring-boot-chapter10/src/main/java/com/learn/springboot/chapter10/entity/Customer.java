package com.learn.springboot.chapter10.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
    private String role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority1=new SimpleGrantedAuthority(this.role);
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(authority1);
        return list;
    }

    /**
     * 账号是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *是否锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账号凭证是否过期
     * @return
     */

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
