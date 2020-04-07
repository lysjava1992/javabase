package com.learn.oauth.server3.dao;

import com.learn.oauth.server3.entity.Customer;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-07 13:30
 **/
public interface CustomerDao {
    /**
     *
     * @param s
     * @return
     */
    Customer selectByUsername(String s);
}
