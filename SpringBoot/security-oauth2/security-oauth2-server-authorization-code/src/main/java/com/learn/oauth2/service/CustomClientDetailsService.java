package com.learn.oauth2.service;

import com.learn.oauth2.model.CustomClientDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomClientDetailsService implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        CustomClientDetails client=new CustomClientDetails();
        client.setClientId(clientId);
        client.setClientSecret("$2a$10$z7zPpUeOYk0UjQd5kATi8uuMgmeWwNrxAgqS4CUBNMPFhSM4uzoeW");
        Set<String> type=new HashSet();
        type.add("authorization_code");
        client.setAuthorizedGrantTypes(type);
        Set<String> url=new HashSet();
        url.add("/login");
        client.setRegisteredRedirectUri(url);
        client.setAccessTokenValiditySeconds(1800);
        client.setAccessTokenValiditySeconds(60*60*2);
        client.setScoped(true);
        Set<String> scope=new HashSet();
        scope.add("all");
        client.setScope(scope);
        Collection<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority("admin"));
        client.setAuthorities(list);
        return client;
    }
}
