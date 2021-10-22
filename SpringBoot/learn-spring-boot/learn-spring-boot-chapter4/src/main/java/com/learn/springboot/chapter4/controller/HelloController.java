package com.learn.springboot.chapter4.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 11:25
 **/
@Controller
public class HelloController {

    @GetMapping({"","/"})
    public String index(){
        return "index";
    }
    @GetMapping("cors")
    public String cors(){
        return "cors";
    }
    @ResponseBody
    @GetMapping({"err"})
    public Integer  err(){
        int i=1;
        return i=i/0;
    }
}
