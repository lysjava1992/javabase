package com.learn.spring.ssm.v2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-19 16:04
 **/
@RestControllerAdvice
public class ExceptionHandler {




    /**
     * 捕获Exception
     * @param e
     * @return json格式类型
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> customExceptionHandler(Exception e) {
        System.out.println(e.getClass());
        Map<String, Object> map = new HashMap<>();
        map.put("status","异常信息" );
        map.put("msg", e.getMessage());
        return map;
    }

}
