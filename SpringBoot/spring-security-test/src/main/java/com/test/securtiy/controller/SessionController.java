package com.test.securtiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/session")
@RestController
public class SessionController {
    // 在SecurityConfig中配置后 可直接引用
    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * sessionRegistry.getAllPrincipals() 所有在线用户
     * sessionRegistry.getAllPrincipals().size() 在线人数
     * 在线人数 是以username区分的，即一个账号多出登录只算一个
     *返回的是
     */
    @GetMapping
    public Object getOnlinUser(){
        return sessionRegistry.getAllPrincipals();
    }

    /**
     *
     *获取所有在线session
     **/
    @GetMapping("all")
    public List<SessionInformation> getOnlinSession(){

        List<SessionInformation> sessionsInfo;
        sessionsInfo = sessionRegistry.getAllSessions(  SecurityContextHolder.getContext().getAuthentication().getPrincipal(), true);
        return sessionsInfo;
    }

    /**
     *
     *踢出
     **/
    @GetMapping("del/{sessionId}")
    public String getOnlinSession(@PathVariable String sessionId){

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
