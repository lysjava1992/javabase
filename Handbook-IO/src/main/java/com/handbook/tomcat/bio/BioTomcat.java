package com.handbook.tomcat.bio;

import com.handbook.tomcat.bio.servlet.HelloServlet;
import com.handbook.tomcat.bio.servlet.ResourcesServlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 *  使用jdk 原生io 实现的一个简单的tomcat服务器
 *    1.实现 http协议 POST / GET 请求
 *    2.可解析请求参数
 *    3.可返回静态资源
 *    4.返回数据
 * @author: Mr.Luan
 * @create: 2019-10-25 15:25
 **/

public class BioTomcat {
    private int port ;
    private ServerSocket server;
    Map<String,Servlet> servletMap=new HashMap<>();
    public BioTomcat(int port) {
        this.port = port;

    }
    private void start() {
        intServletConfig();
        try {
            server=new ServerSocket(port);
            System.out.println("服务器启动，端口号"+port);
            while (true){
                Socket socket=server.accept();
                MeHttpRequest request=new MeHttpRequest(socket.getInputStream());
                MeHttpResponse response=new MeHttpResponse(socket.getOutputStream());
                distribute(request,response);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void distribute(MeHttpRequest request, MeHttpResponse response) {
                  String url=request.getUrl();
                  if(url.endsWith(".html")
                     ||url.endsWith(".js")
                     ||url.endsWith(".png")
                     ||url.endsWith(".css")){
                      Servlet servlet=servletMap.get("file");
                      servlet.service(request,response);
                  }else {
                      Servlet servlet=servletMap.get("string");
                      servlet.service(request,response);
                  }

    }

    /**
     *  初始化配置
     *  即请求路径 与 逻辑处理的对照关系
     *  在SSM中相当于 在web.xml文件中配置的
     *  <servlet>
     * <servlet-name>spring-dispatcher</servlet-name>
     * <servlet-class>org.springframework.web.servlet.DispatcherServlet
     *    </servlet-class>
     *  </servlet>
     *    <servlet-mapping>
     *     <servlet-name>spring-dispatcher</servlet-name>
     *     <url-pattern>/</url-pattern>
     * </servlet-mapping>
     *  配置一个Servlet接收所有请求，交给DispatcherServlet处理
     *  此案例中一个servelt负责一种请求的处理
     *
     * 实际中用反射来处理
     *
     */
    private void intServletConfig() {
           servletMap.put("string",new HelloServlet());
           servletMap.put("file",new ResourcesServlet());
    }

    public static void main(String[] args) throws IOException {
        new BioTomcat(9001).start();
    }


}
