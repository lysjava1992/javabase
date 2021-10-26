package com.learn.springboot.chapter9.controller;

import com.learn.springboot.chapter9.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-30 11:40
 **/
@RequestMapping("ok")
@RestController
public class HelloController {

    @GetMapping("")
    public Result ok(){
        return new Result(200,"无需登录","/ok");
    }


    @GetMapping("/hello")
    public Result hello(){
        return new Result(200,"无需登录","/ok/hello");
    }


}
