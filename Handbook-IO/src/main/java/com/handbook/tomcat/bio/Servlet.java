package com.handbook.tomcat.bio;



/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-25 16:13
 **/
public abstract class Servlet {
    public abstract void  doGet(MeHttpRequest request,MeHttpResponse response);
    public abstract void  doPost(MeHttpRequest request,MeHttpResponse response);
    public void service(MeHttpRequest request,MeHttpResponse response){
        if("GET".equals(request.getMethod())){
            doPost(request,response);
        }else {
            doGet(request,response);
        }
    }
}
