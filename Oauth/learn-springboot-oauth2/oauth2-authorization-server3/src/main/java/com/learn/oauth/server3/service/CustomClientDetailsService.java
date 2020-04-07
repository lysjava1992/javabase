package com.learn.oauth.server3.service;

import com.learn.oauth.server3.dao.ClientDetailsDao;
import com.learn.oauth.server3.entity.CustomerClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-07 13:39
 **/
@Service
public class CustomClientDetailsService implements ClientDetailsService{
    @Autowired
    ClientDetailsDao clientDetailsDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        CustomerClientDetails clientDetails=clientDetailsDao.selectByClientId(s);
        if (clientDetails == null) {
            throw new ClientRegistrationException("该客户端不存在");
        }

        clientDetails.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
        return clientDetails;
    }
}
