package com.shiro.web.controller;

import com.shiro.web.model.Result;
import com.shiro.web.service.SessionService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;


@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;


    @GetMapping("/find")
    public List<Session> find(){
        return sessionService.find();
    }

    @GetMapping("/del")
    public Result find(String id){
        sessionService.remote(id);
        return new Result(true);
    }
}
