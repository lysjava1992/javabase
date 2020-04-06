package com.learn.oauth2resourceserver1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName HelloController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/5 14:05
 * @Version 1.0
 **/
@RestController
public class HelloController {

        @GetMapping("/admin/hello")
        public String admin(){
            return "hello-admin"+ UUID.randomUUID().toString();

        }
        @GetMapping("/user/hello")
        public String user(){
            return "hello-user:[12025630]";

        }

        @GetMapping("/hello")
        public String hello() {
            return "Hello !!!";

        }
    @GetMapping("/scopes/test")
    public String test() {
        return "Hello scopes";

    }
}
