package com.learn.book.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServerV5 {
    public static void main(String[] args) {
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();

        ServerBootstrap bootstrap=new ServerBootstrap();
       try {
           bootstrap.group(bossGroup, workGroup)
                   .channel(NioServerSocketChannel.class)
                   .option(ChannelOption.SO_BACKLOG,1024)
                   .childHandler(new ChildChannelHandler());
           // 绑定端口
           ChannelFuture future= bootstrap.bind(8011).sync();
           System.out.println("Server is starting ....");
           //等待关闭
           future.channel().closeFuture().sync();
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           bossGroup.shutdownGracefully();
           workGroup.shutdownGracefully();
       }
    }
}
