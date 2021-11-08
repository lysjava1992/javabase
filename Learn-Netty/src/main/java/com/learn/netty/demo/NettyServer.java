package com.learn.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author admin
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        //接收连接
        NioEventLoopGroup boos=new NioEventLoopGroup();
        //数据读取
        NioEventLoopGroup workers=new NioEventLoopGroup();
        serverBootstrap
                   .group(boos,workers)
                   .channel(NioServerSocketChannel.class)
                   .childHandler(new ChannelInitializer<NioSocketChannel>() {
                       @Override
                       protected void initChannel(NioSocketChannel nsc) throws Exception {
                           nsc.pipeline().addLast(new StringDecoder());
                           nsc.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                               @Override
                               protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                   System.out.println("接收消息: "+s);
                               }
                           });
                       }

                   })
                   .bind(8633);
    }
}
