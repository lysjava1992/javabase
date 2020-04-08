package com.learn.oauth2.server4.dao;

import com.learn.oauth2.server4.model.CustomClientDetails;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-08 09:10
 **/
public interface CustomClientDetailsDao {
    /**
     *  查询客户端ID
     * @param clientId
     * @return
     */
    CustomClientDetails queryByClientId(String clientId);
}
