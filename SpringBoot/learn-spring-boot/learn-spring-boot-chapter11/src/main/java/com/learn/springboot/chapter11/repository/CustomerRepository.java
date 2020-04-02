package com.learn.springboot.chapter11.repository;

import com.learn.springboot.chapter11.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-02 15:53
 **/
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer getCustomersByUsername(String username);
}
