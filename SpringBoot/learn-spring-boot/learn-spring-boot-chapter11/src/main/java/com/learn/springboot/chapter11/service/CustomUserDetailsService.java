package com.learn.springboot.chapter11.service;

import com.learn.springboot.chapter11.model.Customer;
import com.learn.springboot.chapter11.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-02 16:00
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository repository;
    @Override
    public UserDetails loadUserByUsername(String s)  {
        Customer customer=repository.getCustomersByUsername(s);
        if(customer==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customer;
    }
}
