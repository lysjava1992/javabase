package com.learn.oauth2.server4.dao;

import com.learn.oauth2.server4.model.CustomUserDetails;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-08 09:10
 **/
public interface CustomUserDetailsDao {
    /**
     *  查询用户信息
     * @param username
     * @return
     */
    CustomUserDetails queryByUsername(String username);
}
