package com.learn.springboot.chapter8.repository;

import com.learn.springboot.chapter8.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-27 16:19
 **/
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
