package com.hand.written.demo.controller;

import com.hand.written.demo.service.DemoService;
import com.hand.written.demo.service.TestService;
import com.hand.written.spring.formwork.annotation.Autowired;
import com.hand.written.spring.formwork.annotation.Controller;
import com.hand.written.spring.formwork.annotation.RequestMapping;
import com.hand.written.spring.formwork.annotation.RequestParam;
import com.hand.written.spring.formwork.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName TestController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 9:57
 * @Version 1.0
 **/
@Controller
@RequestMapping("/")
public class TestController {

    @Autowired
    DemoService demoService;
    @Autowired
    TestService testService;

    public void test(HttpServletRequest request, HttpServletResponse response,String str){
       System.out.println(demoService.doDemo(str));
       System.out.println(testService.test(str));

    }

    @RequestMapping("test")
    public ModelAndView modelAndView(HttpServletRequest request, HttpServletResponse response,@RequestParam("str") String str){

        demoService.doDemo(str);
        testService.test(str);
     return out(response,str+"!!!!!!!!!!!!!!!");
    }

    @RequestMapping("/app.html")
    public ModelAndView query(@RequestParam("name") String name){
        demoService.doDemo(name);
        testService.test(name);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Object> model=new HashMap<>();
        model.put("name",name);
        model.put("data",sdf.format(System.currentTimeMillis()));
        model.put("msg", UUID.randomUUID());
        return new ModelAndView("app.html",model);
    }

    private ModelAndView out(HttpServletResponse response,String str){
        try {
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
