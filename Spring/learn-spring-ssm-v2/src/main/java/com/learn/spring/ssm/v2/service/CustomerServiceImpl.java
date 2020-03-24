package com.learn.spring.ssm.v2.service;

import com.learn.spring.ssm.v2.dao.CustomerDao;
import com.learn.spring.ssm.v2.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-24 13:02
 **/
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerDao customerDao;
    @Override
    public Customer findOne(Customer customer) {
        return customerDao.findByName(customer.getUsername());
    }

    @Override
    public Customer add(Customer customer) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sdf.format(System.currentTimeMillis());
        customer.setCreateTime(time);
        customerDao.add(customer);
        return customer;
    }

    @Override
    public void delete(Long id) {
        customerDao.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Transactional(rollbackFor=ArithmeticException.class)
    @Override
    public void deletes(Long[] ids){
        for (int i = 0; i <ids.length ; i++) {
            customerDao.deleteById(ids[i]);
            if(i==1){int m=i/0;}
        }
    }
}
