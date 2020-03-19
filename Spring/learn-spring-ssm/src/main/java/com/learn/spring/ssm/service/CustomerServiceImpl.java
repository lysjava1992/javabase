package com.learn.spring.ssm.service;

import com.learn.spring.ssm.dao.CustomerDao;
import com.learn.spring.ssm.entity.Customer;
import com.learn.spring.ssm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-19 14:38
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

    @Transactional
    @Override
    public void deletes(Long[] ids) {
        for (int i = 0; i <ids.length ; i++) {
            customerDao.deleteById(ids[i]);
            if(i==3){int m=i/0;}
        }
    }

}
