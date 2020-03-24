package com.learn.spring.ssm.v2.service;

import com.learn.spring.ssm.v2.entity.Customer;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-24 13:02
 **/
public interface CustomerService {
    Customer findOne(Customer customer) ;

    Customer add(Customer customer);

    void delete(Long id);

    List<Customer> findAll();

    void deletes(Long[] ids) throws Exception;
}
