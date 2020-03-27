package com.learn.springboot.chapter6.dao2;

import com.learn.springboot.chapter6.entity.Customer;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-27 10:01
 **/
public interface CustomerDaoTwo {
    int insert(Customer customer);

    List<Customer> selectList();

    Customer selectById(Integer id);

    int updateById(Customer user);
}
