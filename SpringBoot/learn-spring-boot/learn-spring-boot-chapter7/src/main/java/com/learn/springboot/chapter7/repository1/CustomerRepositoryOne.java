package com.learn.springboot.chapter7.repository1;

import com.learn.springboot.chapter7.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-27 13:26
 **/
public interface CustomerRepositoryOne extends JpaRepository<Customer,Long> {
    List<Customer> getCustomersByAddr(String addr);

    @Query(value = "select * from user where password=?1",nativeQuery = true)
    List<Customer> customSql(String password);
}