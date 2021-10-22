package com.learn.springboot.chapter5.service;

import com.learn.springboot.chapter5.entity.Customer;
import com.learn.springboot.chapter5.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 16:48
 **/
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository repository;
    @Override
    public Customer add(Customer customer) {
        return repository.add(customer);
    }

    @Override
    public List<Customer> selectList(int type) {
        return repository.selectList(type);
    }

    @Override
    public Customer selectById(Integer id) {
        return repository.selectById(id);
    }

    @Override
    public Customer update(Customer customer) {
        return repository.update(customer);
    }
}
