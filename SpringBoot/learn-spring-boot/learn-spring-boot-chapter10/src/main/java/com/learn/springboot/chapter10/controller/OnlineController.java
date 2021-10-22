package com.learn.springboot.chapter10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-02 10:13
 **/
@RestController
@RequestMapping("session")
public class OnlineController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping
    public Object getOnlinUser(){
        return sessionRegistry.getAllPrincipals();
    }

    @GetMapping("all")
    public List<SessionInformation> getOnlinSession(){

        List<SessionInformation> sessionsInfo;
        sessionsInfo = sessionRegistry.getAllSessions(  SecurityContextHolder.getContext().getAuthentication().getPrincipal(), true);
        return sessionsInfo;
    }

    @GetMapping("del/{sessionId}")
    public String getOnlinSession(@PathVariable  String sessionId){

        List<SessionInformation> sessionsInfo;
        sessionsInfo = sessionRegistry.getAllSessions(  SecurityContextHolder.getContext().getAuthentication().getPrincipal(), true);
        for (SessionInformation sessionInformation:sessionsInfo){
            if(sessionInformation.getSessionId().equals(sessionId)){
                sessionInformation.expireNow();
            }
        }
        return "OK";
    }
}
