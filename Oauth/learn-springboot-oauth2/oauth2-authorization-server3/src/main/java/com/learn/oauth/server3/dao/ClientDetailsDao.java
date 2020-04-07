package com.learn.oauth.server3.dao;

import com.learn.oauth.server3.entity.CustomerClientDetails;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-07 14:26
 **/
public interface ClientDetailsDao {
    CustomerClientDetails selectByClientId(String s);
}
