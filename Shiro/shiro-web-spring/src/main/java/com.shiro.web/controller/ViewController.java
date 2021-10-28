package com.shiro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @RequestMapping(path = { "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(path = {"","/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = {"","/ano"}, method = RequestMethod.GET)
    public String ano() {
        return "ano";
    }
}
