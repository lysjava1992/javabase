package com.learn.oauth.server3.service;

import com.learn.oauth.server3.dao.CustomerDao;
import com.learn.oauth.server3.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-07 13:24
 **/
@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    CustomerDao customerDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer=customerDao.selectByUsername(s);
        if(customer==null){
            throw new UsernameNotFoundException("用户不存在");
        }


        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customer;
    }
}
