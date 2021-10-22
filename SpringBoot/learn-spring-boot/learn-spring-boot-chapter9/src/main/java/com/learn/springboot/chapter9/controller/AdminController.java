package com.learn.springboot.chapter9.controller;

import com.learn.springboot.chapter9.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-30 13:33
 **/
@RequestMapping("/admin")
@RestController
public class AdminController {
    @GetMapping("")
    public Result admin(){
        return new Result(200,"Admin权限","/admin");
    }
    @GetMapping("get")
    public Result get(){
        return new Result(200,"Admin权限","/admin/get");
    }
    @GetMapping("get/get")
    public Result get2(){
        return new Result(200,"Admin权限","/admin/get/get");
    }
}
