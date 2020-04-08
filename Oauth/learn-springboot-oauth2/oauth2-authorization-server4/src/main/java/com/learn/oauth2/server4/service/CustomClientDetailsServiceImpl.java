package com.learn.oauth2.server4.service;

import com.learn.oauth2.server4.dao.CustomClientDetailsDao;
import com.learn.oauth2.server4.model.CustomClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-08 09:22
 **/
@Service
public class CustomClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private CustomClientDetailsDao customClientDetailsDao;
    @Override
    public ClientDetails loadClientByClientId(String clientId)  {
        CustomClientDetails clientDetails=customClientDetailsDao.queryByClientId(clientId);
        if(clientDetails==null){
            throw  new  ClientRegistrationException("非法客户端");
        }
        //加密
        clientDetails.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
        return clientDetails;
    }
}
