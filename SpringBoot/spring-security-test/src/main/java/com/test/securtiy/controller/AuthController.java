package com.test.securtiy.controller;

import com.test.securtiy.model.Result;
import com.test.securtiy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
     @Autowired
     private AuthService authService;

    @PostMapping("/sms/code")
    public Result smsCode(String phone){

        return authService.smsCode(phone);
    }
}
