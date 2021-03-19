package com.learn.springboot.chapter10.dao;
import com.learn.springboot.chapter10.entity.Customer;
/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-31 13:39
 **/
public interface CustomerDao {
    Customer selectByName(String username);
    Customer selectByMoblie(String mobile);
}
