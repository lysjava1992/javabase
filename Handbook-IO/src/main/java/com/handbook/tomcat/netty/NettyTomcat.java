package com.handbook.tomcat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import sun.nio.ch.Net;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName NettyTomcat
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/10/27 15:11
 * @Version 1.0
 **/
public class NettyTomcat {
    private int port ;
    private final static int BOSS_SIZE=Runtime.getRuntime().availableProcessors()*2;
    private final static int WORK_SIZE=100;

    public NettyTomcat(int port) {
        this.port = port;
    }
    private void start() {
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();
        try {
            ServerBootstrap server=new ServerBootstrap();
            server.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel >() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            // 服务端，对请求解码
                            sc.pipeline().addLast("http-decoder",
                                    new HttpRequestDecoder());
                            // 聚合器，把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse
                            sc.pipeline().addLast("http-aggregator",
                                    new HttpObjectAggregator(65536));
                            // 服务端，对响应编码
                            sc.pipeline().addLast("http-encoder",
                                    new HttpResponseEncoder());
                            // 块写入处理器
                            sc.pipeline().addLast("http-chunked",
                                    new ChunkedWriteHandler());
                            // 自定义服务端处理器
                            sc.pipeline().addLast("fileServerHandler",
                                    new HttpServerHandler());
                        }
                    });
            ChannelFuture cf=server.bind(port).sync();
            System.out.println("服务器启动.........");
            cf.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) {
         new NettyTomcat(8080).start();
    }


}
