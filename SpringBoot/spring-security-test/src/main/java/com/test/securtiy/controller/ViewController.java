package com.test.securtiy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @RequestMapping(method ={RequestMethod.GET,RequestMethod.POST} )
    @PostMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }


}