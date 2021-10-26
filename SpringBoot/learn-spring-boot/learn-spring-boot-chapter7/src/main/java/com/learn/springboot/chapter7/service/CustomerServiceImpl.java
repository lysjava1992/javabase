package com.learn.springboot.chapter7.service;

import com.learn.springboot.chapter7.entity.Customer;
import com.learn.springboot.chapter7.repository1.CustomerRepositoryOne;
import com.learn.springboot.chapter7.repository2.CustomerRepositoryTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-27 13:41
 **/
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepositoryOne customerRepository;
    @Autowired
    CustomerRepositoryTwo customerRepositoryTwo;
    @Override
    public List<Customer> list() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> listByDb(Integer type) {
       if(type==1){
           return customerRepository.findAll();
       }
       return customerRepositoryTwo.findAll();
    }
    @Override
    public List<Customer> getByAddr(String addr) {
        return customerRepository.getCustomersByAddr(addr);
    }
    @Override
    public List<Customer> getByPassworda(String password) {
        return customerRepository.customSql(password);
    }


}
