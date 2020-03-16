package com.hand.written.spring.formwork.webmvc.servlet;

import com.hand.written.spring.formwork.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *           启动入口
 * @ClassName DispatcherServlet
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/3 9:52
 * @Version 1.0
 **/
public class DispatcherServlet extends HttpServlet {
    private  final String LOCATION="contextConfigLocation";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context=new ApplicationContext(config.getInitParameter(LOCATION));
    }
}
