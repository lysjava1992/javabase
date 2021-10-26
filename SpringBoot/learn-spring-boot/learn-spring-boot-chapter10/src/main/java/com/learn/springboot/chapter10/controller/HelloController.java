package com.learn.springboot.chapter10.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-02 11:28
 **/
@RequestMapping("/hello")
@RestController
public class HelloController {

    @GetMapping
    public String hello(){
        return "Spring Security 是强大的，且容易定制的，基于Spring开发的实现认证登录与资源授权的应用安全框架。\n" +
                "\n" +
                "SpringSecurity 的核心功能：\n" +
                "\n" +
                "Authentication：认证，用户登陆的验证（解决你是谁的问题）\n" +
                "Authorization：授权，授权系统资源的访问权限（解决你能干什么的问题）\n" +
                "安全防护，防止跨站请求，session 攻击等";
    }
    @RolesAllowed("ROLE_USER")
    @PostMapping
    public String hello2(){
        return "Spring Security 是强大的，且容易定制的，基于Spring开发的实现认证登录与资源授权的应用安全框架。\n" +
                "\n" +
                "此内容为ROLE_USER权限";
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("admin")
    public String hello3(){
        return "Spring Security 是强大的，且容易定制的，基于Spring开发的实现认证登录与资源授权的应用安全框架。\n" +
                "\n" +
                "此内容为ROLE_ADMIN权限"+
                "\n" +
                "最高权限";
    }
}
