package com.learn.springboot.chapter10.controller;

import com.learn.springboot.chapter10.util.AuthImgUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

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


    @ResponseBody
    @GetMapping(value = {"/mobile_code"})
    public String mobileCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("mobile_code","123456");
        request.setAttribute("mobile_code","123456");
        System.out.println("===============发送成功");
        return "ok";
    }
}
