package com.handbook.tomcat.netty;

import com.handbook.tomcat.netty.servlet.HelloServlet;
import com.handbook.tomcat.netty.servlet.ResourcesServlet;
import com.sun.corba.se.spi.activation.Server;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName HttpServerHandler
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/10/27 15:20
 * @Version 1.0
 **/
public class HttpServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
             if(msg instanceof HttpRequest){
                 FullHttpRequest request= (FullHttpRequest) msg;
                 NettyRequest nettyRequest=new NettyRequest(ctx,request);
                 NettyResponse nettyResponse=new  NettyResponse(ctx,request);
                 String url=request.uri();

                 if(url.endsWith(".html")
                         ||url.endsWith(".js")
                         ||url.endsWith(".png")
                         ||url.endsWith(".css")){
                     Servlet servlet=new ResourcesServlet();
                     servlet.service(nettyRequest,nettyResponse);
                 }else {
                     Servlet servlet=new HelloServlet();
                     servlet.service(nettyRequest,nettyResponse);
                 }
             }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
         cause.printStackTrace();
         ctx.close();
    }
}

