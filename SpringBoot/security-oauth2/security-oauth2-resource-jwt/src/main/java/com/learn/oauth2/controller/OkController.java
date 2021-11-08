package com.learn.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
public class OkController {
    @GetMapping("/user")
    public String ok(){
        return "SUCCESS";
    }
}
