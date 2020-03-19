package com.learn.spring.ssm.service;

import com.learn.spring.ssm.entity.Customer;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-19 14:25
 **/
public interface CustomerService {
    Customer findOne(Customer customer) ;

    Customer add(Customer customer);

    void delete(Long id);

    List<Customer> findAll();

    void deletes(Long[] ids);
}
