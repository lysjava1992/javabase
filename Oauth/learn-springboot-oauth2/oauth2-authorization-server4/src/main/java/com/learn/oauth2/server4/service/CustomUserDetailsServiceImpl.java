package com.learn.oauth2.server4.service;

import com.learn.oauth2.server4.dao.CustomClientDetailsDao;
import com.learn.oauth2.server4.dao.CustomUserDetailsDao;
import com.learn.oauth2.server4.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @description:
 *  用户登录 重写 UserDetailsService
 * @author: Mr.Luan
 * @create: 2020-04-08 09:16
 **/
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private CustomUserDetailsDao customUserDetailsDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) {
        CustomUserDetails userDetails=customUserDetailsDao.queryByUsername(username);
        if(userDetails==null){
             throw new UsernameNotFoundException("用户名不存在");
        }
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        return userDetails;
    }
}
