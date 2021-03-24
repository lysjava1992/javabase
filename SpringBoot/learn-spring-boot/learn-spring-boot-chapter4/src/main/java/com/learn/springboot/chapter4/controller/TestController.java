package com.learn.springboot.chapter4.controller;

import com.learn.springboot.chapter4.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/get/ok")
    public String getTest(){
        return "OK";
    }

    @GetMapping("/get/void")
    public void testVoid(){
       System.out.println("-------void-------");
    }

    @PostMapping("/post")
    public Result postTest(@RequestParam String msg){
         return new Result(msg,true);
    }
}
