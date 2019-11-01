package com.handbook.tomcat.netty.servlet;


import com.handbook.tomcat.netty.NettyRequest;
import com.handbook.tomcat.netty.NettyResponse;
import com.handbook.tomcat.netty.Servlet;

import java.io.IOException;

/**
 * @description:
 *
 * @author: Mr.Luan
 * @create: 2019-10-25 16:23
 **/
public class ResourcesServlet extends Servlet {
    @Override
    public void doGet(NettyRequest request, NettyResponse response) {
     String basePath= ResourcesServlet.class.getProtectionDomain().getCodeSource().getLocation().getPath();
         String url=request.getUrl();
        try {
            response.writeFile(basePath+url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(NettyRequest request, NettyResponse response) {

    }
}
