package com.learn.oauth2.server4.model;

import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description:
 *   自定义Security的用户
 * @author: Mr.Luan
 * @create: 2020-04-08 08:46
 **/
@Data
public class CustomUserDetails implements UserDetails{
    private Long id;
    private String username;
    private String password;
    private String createTime;
    private String addr;
    private String role;
    private String mobile;

    /**
     * 用户权限列表
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority(this.role));
        return list;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否被锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
