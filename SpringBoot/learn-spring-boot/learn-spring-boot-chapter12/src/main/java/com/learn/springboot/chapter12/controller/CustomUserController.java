package com.learn.springboot.chapter12.controller;

import com.learn.springboot.chapter12.entity.CustomUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomUserController {
    @PostMapping("user")
    public CustomUser save( @Validated @RequestBody  CustomUser user){

          user.setId(System.currentTimeMillis());
        return  user;
    }
}
