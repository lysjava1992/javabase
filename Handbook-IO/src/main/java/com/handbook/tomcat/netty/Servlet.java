package com.handbook.tomcat.netty;


import io.netty.handler.codec.http.HttpMethod;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-25 16:13
 **/
public abstract class Servlet {
    public abstract void  doGet(NettyRequest request,NettyResponse response);
    public abstract void  doPost(NettyRequest request,NettyResponse response);
    public void service(NettyRequest request,NettyResponse response){
        if(request.getMethod()== HttpMethod.POST){
            doGet(request,response);
        }else {
            doGet(request,response);
        }
    }
}
