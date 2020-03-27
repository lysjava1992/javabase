package com.learn.springboot.chapter6.service;

import com.learn.springboot.chapter6.dao1.CustomerDaoOne;
import com.learn.springboot.chapter6.dao2.CustomerDaoTwo;
import com.learn.springboot.chapter6.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-27 10:00
 **/
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerDaoOne customerDao;

    @Autowired
    private CustomerDaoTwo customerDaoTwo;
    @Override
    public Customer add(Customer customer) {
       int count= customerDao.insert(customer);
        return null;
    }

    @Override
    public List<Customer> selectList(Integer type) {
        List<Customer> list=null;
        if(type==1){
           list= customerDao.selectList();
        }else {
            list=customerDaoTwo.selectList();
        }
        return list;
    }

    @Override
    public Customer selectById(Integer id) {
        Customer customer=customerDao.selectById(id);
        return null;
    }

    @Override
    public Customer update(Customer user) {
      int count=customerDao.updateById(user);
        return null;
    }
}
