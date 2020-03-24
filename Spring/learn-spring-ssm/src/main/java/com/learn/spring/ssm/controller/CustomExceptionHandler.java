package com.learn.spring.ssm.controller;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-19 16:04
 **/
@ControllerAdvice
public class CustomExceptionHandler {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initWebBinder(WebDataBinder binder){
        //对日期的统一处理
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
        //添加对数据的校验
        //binder.setValidator();
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("key",  "系统默认值");
    }

    /**
     * 捕获Exception
     * @param e
     * @return json格式类型
     */
    @ResponseBody
    @ExceptionHandler({Exception.class}) //指定拦截异常的类型
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //自定义浏览器返回状态码
    public Map<String, Object> customExceptionHandler(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("status","异常信息" );
        map.put("msg", e.getMessage());
        return map;
    }

//    /**
//
//     * @param e
//     * @return 视图
//     */
//    @ExceptionHandler({Exception.class})
//    public ModelAndView customModelAndViewExceptionHandler(Exception e) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("status","异常信息" );
//        map.put("msg", e.getMessage());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error");
//        modelAndView.addObject(map);
//        return modelAndView;
//    }
}
