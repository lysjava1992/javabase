package com.learn.springboot.chapter10.controller;

import com.learn.springboot.chapter10.util.AuthImgUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-31 10:06
 **/
@RequestMapping("")
@Controller
public class LoginController {
    /**
     * 返回登录页面
     * @return
     */
    @GetMapping(value = {"/login",""})
    public String login(HttpServletRequest request){
        return "login";
    }


    @GetMapping(value = {"/index"})
    public String index(Model model){

        return "index";
    }

    @GetMapping(value = {"/code"})
    public void code(HttpServletRequest request,HttpServletResponse response) throws IOException {
      AuthImgUtils.service(request,response);
    }
}
