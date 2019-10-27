package com.handbook.chat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ChatNettyServer
 *    netty 对jdk BIO的API封装
 *    netty 版本 4. 版本是当前可用的维护中版本
 *               5. 版本利用了AIO已经不再维护放弃
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/10/27 10:42
 * @Version 1.0
 *
 * ChannelOption.SO_BACKLOG, 1024
 *                 BACKLOG用于构造服务端套接字ServerSocket对象，
 *                 标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。
 *                 如果未设置或所设置的值小于1，Java将使用默认值50。
 * ChannelOption.SO_KEEPALIVE, true
 *                  是否启用心跳保活机制。
 *                  在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
 * ChannelOption.TCP_NODELAY, true
 *                 在TCP/IP协议中，无论发送多少数据，总是要在数据前面加上协议头，
 *                 同时，对方接收到数据，也需要发送ACK表示确认。
 *                 为了尽可能的利用网络带宽，TCP总是希望尽可能的发送足够大的数据。
 *                 这里就涉及到一个名为Nagle的算法，该算法的目的就是为了尽可能发送大块数据，避免网络中充斥着许多小数据块。
 *                 TCP_NODELAY就是用于启用或关于Nagle算法。
 *                 如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；
 *                 如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false。
 * ChannelOption.SO_REUSEADDR, true
 *              SO_REUSEADDR允许启动一个监听服务器并捆绑其众所周知端口，即使以前建立的将此端口用做他们的本地端口的连接仍存在。这通常是重启监听服务器时出现，若不设置此选项，则bind时将出错。
 *              SO_REUSEADDR允许在同一端口上启动同一服务器的多个实例，只要每个实例捆绑一个不同的本地IP地址即可。对于TCP，我们根本不可能启动捆绑相同IP地址和相同端口号的多个服务器。
 *              SO_REUSEADDR允许单个进程捆绑同一端口到多个套接口上，只要每个捆绑指定不同的本地IP地址即可。这一般不用于TCP服务器。
 *              SO_REUSEADDR允许完全重复的捆绑：当一个IP地址和端口绑定到某个套接口上时，还允许此IP地址和端口捆绑到另一个套接口上。一般来说，这个特性仅在支持多播的系统上才有，而且只对UDP套接口而言（TCP不支持多播）
 * ChannelOption.SO_RCVBUF  AND  ChannelOption.SO_SNDBUF
 *             定义接收或者传输的系统缓冲区buf的大小，
 *
 *  LineBasedFrameDecoder解码器
 *       LineBasedFrameDecoder是回车换行解码器，
 *       如果用户发送的消息以回车换行符作为消息结束的标识，则可以直接使用Netty的LineBasedFrameDecoder对消息进行解码，
 *       只需要在初始化Netty服务端或者客户端时将LineBasedFrameDecoder正确的添加到ChannelPipeline中即可
 *       LineBasedFrameDecoder的工作原理是它依次遍历ByteBuf中的可读字节，判断看是否有“\n”或者“\r\n”，如果有，就以此位置为结束位置，
 *       从可读索引到结束位置区间的字节就组成了一行。它是以换行符为结束标志的解码器，支持携带结束符或者不携带结束符两种解码方式，同时支持配置单行的最大长度。如果连续读取到最大长度后仍然没有发现换行符，就会抛出异常，同时忽略掉之前读到的异常码流。防止由于数据报没有携带换行符导致接收到ByteBuf无限制积压，引起系统内存溢出。
 *  DelimiterBasedFrameDecoder
 *        DelimiterBasedFrameDecoder是分隔符解码器，用户可以指定消息结束的分隔符，
 *        它可以自动完成以分隔符作为码流结束标识的消息的解码。
 *        回车换行解码器实际上是一种特殊的DelimiterBasedFrameDecoder解码器。
 * FixedLengthFrameDecoder解码器
 *       FixedLengthFrameDecoder是固定长度解码器
 *       它能够按照指定的长度对消息进行自动解码，
 *       开发者不需要考虑TCP的粘包/拆包等问题，
 *       对于定长消息，如果消息实际长度小于定长，则往往会进行补位操作，
 *       它在一定程度上导致了空间和资源的浪费。但是它的优点也是非常明显的，编解码比较简单，因此在实际项目中仍然有一定的应用场景。
 *LengthFieldBasedFrameDecoder解码器
 *        自定义长度帧解码器
 *
 **/
public class ChatNettyServer {
    private int port;
    /**
     * 主线程池bossGroup线程数
     * 负责连接轮询
     *  一般为CPU核数*2
     *
     */
    private final static int BOSSGROUP_SIZE=Runtime.getRuntime().availableProcessors()*2;
    /**
     * 工作线程数
     */
    private final static int WORKGROUP_SIZE=100;

    public ChatNettyServer(int port) {
        this.port = port;
    }

    private void start() {
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();
        try {
            //netty提供启动类
            ServerBootstrap server=new ServerBootstrap();
            //绑定线程
            server.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    //处理
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            //编解码器使用String
                          //  sc.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            sc.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                            sc.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                            sc.pipeline().addLast(new ChatServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG,128);
            //绑定等待连接
            ChannelFuture cf=server.bind(port).sync();
            System.out.println("服务器启动");
            //关闭连接
            cf.channel().closeFuture().sync();

        }catch (Exception e){

        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }



    }
    public static void main(String[] args) throws Exception {
        new ChatNettyServer(8888).start();
    }

}
