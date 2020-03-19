package com.learn.spring.ssm.controller;

import com.learn.spring.ssm.entity.Customer;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName BaseController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/19 10:09
 * @Version 1.0
 **/
@RequestMapping("")
@Controller
public class BaseController {

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("index1")
    public ModelAndView index(HttpServletRequest request,
                              HttpServletResponse response) {
        Date date = new Date();
        return new ModelAndView("index1", "time", date);
    }

    @ResponseBody
    @GetMapping("str")
    public String str(HttpServletRequest request,
                      HttpServletResponse response) {

        return "返回字符串";
    }

    @ResponseBody
    @GetMapping("json")
    public JSONObject json(HttpServletRequest request,
                           HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1", "value1");
        jsonObject.put("id", "1L");
        jsonObject.put("username", "张三");
        jsonObject.put("password", "张三");
        jsonObject.put("createTime", "2020-03-19");

        return jsonObject;
    }

    @ResponseBody
    @GetMapping("pojo")
    public Customer pojo(HttpServletRequest request,
                         HttpServletResponse response) {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("json");
        customer.setPassword(123456 + "");
        customer.setCreateTime("2020-03-19");
        return customer;
    }
}
