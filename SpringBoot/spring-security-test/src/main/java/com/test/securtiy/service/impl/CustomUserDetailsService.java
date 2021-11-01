package com.test.securtiy.service.impl;

import com.test.securtiy.model.CustomUser;
import com.test.securtiy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *  此处根据表单传递的用户名
 *   查询用户密码
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s)  {
             System.out.println(s);
            CustomUser user=userRepository.findByUsername(s);
            if(user==null){
             throw new UsernameNotFoundException("用户不存在");
            }
        return user;
    }
}
