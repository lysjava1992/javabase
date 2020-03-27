package com.learn.springboot.chapter2.controller;

import com.learn.springboot.chapter2.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName IndexController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/22 11:08
 * @Version 1.0
 **/
@Controller
public class IndexController {
    @Autowired
    EchoService service;
    @GetMapping({"/",""})
    public String index(Model model){

        service.echo();
        model.addAttribute("string",new StringUtil());
        //model.addAttribute("msg","Hello Thymeleaf!!!!!!!!!!!!");
        return "index";
    }
    @ModelAttribute("msg")
    public String value(){
        return "Hello Thymeleaf!!!!!!!!!!!!";
    }

    /**
     *
     * 返回的是index页面
     * @return
     */
    @GetMapping("/index")
    @ModelAttribute("msg")
    public String value2(Model model){
        model.addAttribute("string",new StringUtil());
        return "Hello Thymeleaf";
    }

    public static class StringUtil{
        public StringUtil() {
        }
        public boolean isNotBlank(String value){
            return StringUtils.hasText(value);
        }
    }
}
