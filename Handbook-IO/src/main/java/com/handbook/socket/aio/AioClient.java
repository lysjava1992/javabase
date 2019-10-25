package com.handbook.socket.aio;

import jdk.management.resource.internal.CompletionHandlerWrapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * @description:  Java AIO  异步非阻塞
 *  是趋势和发展方向 目前在windows系统已真正实现AIO ，但是liunx并未真正实现AIO是伪AIO性能不是很好
 *  大多数服务器还是在liunx上，所以当前还是NIO为主流
 *  ExecutorService	线程池
 *  AsynchronousChannelGroup	线程组
 *  AsynchronousServerSocketChannel	AIO的server对象
 *  CompletionHandler	异步IO的处理模板
 * @author: Mr.Luan
 * @create: 2019-10-24 16:49
 **/
public class AioClient {
    private int port;
    private String ip;
    private String name;
    private AsynchronousSocketChannel client;

    public AioClient(int port, String ip, String name) {
        this.port = port;
        this.ip = ip;
        this.name = name;
    }
    private void start() {
        try {
            client=AsynchronousSocketChannel.open();
            client.connect(new InetSocketAddress(ip,port),null , new CompletionHandler<Void, Void>() {
                @Override
                public void completed(Void result, Void attachment) {
                    try {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.put((name + "请求").getBytes());
                        buffer.flip();
                        client.write(buffer).get();
                        buffer.clear();
                        client.read(buffer).get();
                        buffer.flip();
                      System.out.println(new String(buffer.array()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void failed(Throwable exc, Void attachment) {

                }
            });

             Thread.sleep(1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new AioClient(8888,"127.0.0.1","【Client_Aio】->").start();

    }


}
