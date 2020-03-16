package com.hand.written.demo.action;

import com.hand.written.demo.service.DemoService;
import com.hand.written.demo.service.TestService;
import com.hand.written.spring.formwork.annotation.Autowired;
import com.hand.written.spring.formwork.annotation.Controller;
import com.hand.written.spring.formwork.annotation.RequestMapping;
import com.hand.written.spring.formwork.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName DemoAction
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/2 10:24
 * @Version 1.0
 **/
@RequestMapping("/demo")
@Controller
public class DemoAction {
    @Autowired
    DemoService demoService;
    @Autowired
    TestService testService;

    public void query(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam("name") String name){
        testService.doTest();
        System.out.println(demoService);
        String result=demoService.get(name);
        System.out.println(result);
//        try {
//            response.getWriter().write(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}
