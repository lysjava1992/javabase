package com.learn.springboot.chapter6.service;

import com.learn.springboot.chapter6.entity.Customer;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-27 09:58
 **/
public interface CustomerService {
    public Customer add(Customer customer) ;

    List<Customer> selectList(Integer type);

    Customer selectById(Integer id);

    Customer update(Customer user);
}
