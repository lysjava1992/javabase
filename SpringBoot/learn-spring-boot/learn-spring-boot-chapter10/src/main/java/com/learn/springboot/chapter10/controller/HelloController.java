package com.learn.springboot.chapter10.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-30 16:48
 **/
@RestController
public class HelloController {

    @GetMapping("")
    public String hello() {
        return "hello";
    }

    @RolesAllowed("user")
    @GetMapping("/user")
    public String user() {
        return "hello jwt !";
    }

    @RolesAllowed("admin")
    @GetMapping("/admin")
    public String admin() {
        return "hello admin !";
    }
}
