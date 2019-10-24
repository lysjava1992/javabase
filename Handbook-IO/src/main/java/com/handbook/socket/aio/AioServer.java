package com.handbook.socket.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-24 16:55
 **/
public class AioServer {
   private int port;
   private AsynchronousServerSocketChannel server;

    public AioServer(int port) {
        this.port = port;
    }

    private void listen() throws IOException {
        server = AsynchronousServerSocketChannel.open();
        //配置Socket
        server.setOption(StandardSocketOptions.SO_REUSEADDR,true);
        server.setOption(StandardSocketOptions.SO_RCVBUF,16*1024);
        server.bind(new InetSocketAddress("127.0.0.1",port));
        System.out.println("启动监控......");
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                ByteBuffer buffer=ByteBuffer.allocate(1024);
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                System.out.println("waiting....");
                buffer.clear();
                try {
                    result.read(buffer).get();
                    buffer.flip();
                    String str=new String(buffer.array());
                    buffer.clear();
                    String[] strs=str.split("->");
                    String name="【Server_AIO】";
                    if(strs.length==2){
                        buffer.put((name+"应答——》"+strs[0]).getBytes());
                        buffer.flip();
                    }else {
                        buffer.put((name+"无效请求").getBytes());
                        buffer.flip();

                    }
                    result.write(buffer).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void failed(Throwable exc, Object attachment) {
             exc.printStackTrace();
            }
        });
        try {
            // AIO不会阻塞 因此必须在此阻塞
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        new AioServer(8888).listen();
    }

}
