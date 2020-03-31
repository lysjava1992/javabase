package com.learn.springboot.chapter10.service;


import com.learn.springboot.chapter10.dao.CustomerDao;
import com.learn.springboot.chapter10.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-31 11:24
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomerDao customerDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("===================");
        Customer customer=customerDao.selectByName(username);
          if(customer==null){
              throw new UsernameNotFoundException("用户不存在");
          }
        return customer;
    }
}
