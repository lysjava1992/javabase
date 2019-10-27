package com.handbook.tomcat.netty.servlet;


import com.alibaba.fastjson.JSON;
import com.handbook.tomcat.netty.NettyRequest;
import com.handbook.tomcat.netty.NettyResponse;
import com.handbook.tomcat.netty.Servlet;

import java.io.IOException;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-25 16:21
 **/
public class HelloServlet extends Servlet {
    @Override
    public void doGet(NettyRequest request, NettyResponse response) {
        try {
            response.write(JSON.toJSONString(request.getParam()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doPost(NettyRequest request, NettyResponse response) {
    }
}
