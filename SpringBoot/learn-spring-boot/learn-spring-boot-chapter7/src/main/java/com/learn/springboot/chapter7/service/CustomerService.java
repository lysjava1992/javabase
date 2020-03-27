package com.learn.springboot.chapter7.service;

import com.learn.springboot.chapter7.entity.Customer;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-27 13:34
 **/
public interface CustomerService {
    List<Customer> list();

    List<Customer> getByAddr(String addr);

    List<Customer> getByPassworda(String password);

    List<Customer> listByDb(Integer type);
}
