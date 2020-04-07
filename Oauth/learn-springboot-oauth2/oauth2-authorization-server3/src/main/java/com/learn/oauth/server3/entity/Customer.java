package com.learn.oauth.server3.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 实现 UserDetails
 *
 **/
@Data
public class Customer implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String createTime;
    private String addr;
    private String role;

    /**
     * 将用户的权限封装注册
     * @return
     **/
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

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
