package com.handbook.tomcat.bio.servlet;

import com.handbook.tomcat.bio.MeHttpRequest;
import com.handbook.tomcat.bio.MeHttpResponse;
import com.handbook.tomcat.bio.Servlet;

import java.io.IOException;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-25 16:21
 **/
public class HelloServlet extends Servlet{
    @Override
    public void doGet(MeHttpRequest request, MeHttpResponse response) {
        String result=request.getStringParam();
        try {
            response.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doPost(MeHttpRequest request, MeHttpResponse response) {

    }
}
