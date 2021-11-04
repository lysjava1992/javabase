package com.test.securtiy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
    @RequestMapping(value = {"/","/index"},method ={RequestMethod.GET,RequestMethod.POST} )
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }


    @GetMapping("err")
    public String err(){
        return "err";
    }
    @GetMapping("/session")
    public String session(){
        return "session";
    }
}
