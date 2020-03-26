package com.learn.springboot.chapter4.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 12:01
 **/
//@ControllerAdvice
public class ExceptionController {
    /**
     * 返回的是json
     * 非页面
     */
//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public Map<String, Object> handleException(Exception e) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notexist");
//        map.put("message", e.getMessage());
//        return map;
//    }

    /**
     *
     *  但是 404错误是捕捉不到的
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {
        request.setAttribute("custom", "定制数据");
        return "error/6xx.html";
    }
}
