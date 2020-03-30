package com.learn.springboot.chapter9.controller;

import com.learn.springboot.chapter9.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-30 13:32
 **/
@RequestMapping("/normal")
@RestController
public class NormalController {
    @GetMapping("")
    public Result admin(){
        return new Result(200,"需登录","");
    }
    @GetMapping("get")
    public Result get(){
        return new Result(200,"需登录","/normal/get");
    }
    @GetMapping("get/get")
    public Result get2(){
        return new Result(200,"需登录","/normal/get/get");
    }
}
