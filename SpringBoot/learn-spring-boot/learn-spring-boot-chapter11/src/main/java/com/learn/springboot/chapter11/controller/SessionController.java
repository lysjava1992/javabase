package com.learn.springboot.chapter11.controller;

import com.learn.springboot.chapter11.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-03 08:52
 **/
@RequestMapping("/se")
@RestController
public class SessionController {
    @Autowired
    SessionRegistry sessionRegistry;


    @GetMapping
    public List<SessionInformation> get(){
        return sessionRegistry.getAllSessions(Customer.class,true);
    }

    @GetMapping("/users")
    public List<Object> getSize(){
        return sessionRegistry.getAllPrincipals();
    }
}
