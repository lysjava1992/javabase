package com.learn.spring.ssm.dao;

import com.learn.spring.ssm.entity.Customer;

import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CostomerDao
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/19 10:05
 * @Version 1.0
 **/
public interface CustomerDao {
      Customer findById(Long id);
      List<Customer> findAll();
    
}
