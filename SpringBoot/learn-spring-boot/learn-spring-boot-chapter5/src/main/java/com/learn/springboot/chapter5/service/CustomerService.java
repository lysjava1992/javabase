package com.learn.springboot.chapter5.service;

import com.learn.springboot.chapter5.entity.Customer;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 16:44
 **/
public interface CustomerService {
    Customer add(Customer customer);

    List<Customer> selectList(int type);

    Customer selectById(Integer id);

    Customer update(Customer user);
}
