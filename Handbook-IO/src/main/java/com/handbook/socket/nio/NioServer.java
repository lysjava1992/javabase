package com.handbook.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:  java 原生NIO 同步非阻塞
 *      与BIO 不同 NIO 不会阻塞在socket.accept()方法 需要一直轮询
 *      与BIo 不同 NIO 不能直接操作in OutputStream/InputStream()
 *            通过缓冲区来实现
 * @author: Mr.Luan
 * @create: 2019-10-24 13:32
 **/
public class NioServer {
    private int port;
    private ServerSocketChannel server;
    private Selector selector;
    private String name;
    private Charset charset=Charset.forName("UTF-8");
    public NioServer(int port,String name) throws IOException {
        this.port = port;
        this.name=name;
    }

    private void start() throws IOException {
        //打开通道
        server=ServerSocketChannel.open();
      //建立连接
        server.bind(new InetSocketAddress(port));
      //配置 非阻塞
        server.configureBlocking(false);

      //绑定选择器，注册连接时间
      selector=Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
      listening();
    }

    /**
     * 一直轮询监听
     */
    private void listening() {
        System.out.println("开始监听......");
        try {
            while (true){
                if(selector.select()==0){continue;}
                Set<SelectionKey> keys =selector.selectedKeys();
                Iterator<SelectionKey> iterator=keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key=iterator.next();
                    process(key);
                    iterator.remove();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 真正的逻辑业务处理
     * @param key
     */
    private void process(SelectionKey key) {
        ByteBuffer buffer=ByteBuffer.allocate(1024);
     try {
         if(key.isAcceptable()){
             //客户端连接事件
             SocketChannel client=server.accept();
             client.configureBlocking(false);
             //连接 注册读事件
             client.register(selector,SelectionKey.OP_READ);
         }else if(key.isReadable()){
             //可读事件
             SocketChannel client= (SocketChannel) key.channel();
             StringBuilder content=new StringBuilder();
             while (client.read(buffer)>0){
                 buffer.flip();
                 content.append(charset.decode(buffer));
                 buffer.clear();
             }
             String result=content.toString();
             String[] strs=result.split("->");
             if(strs.length==2){
                 result=  name+"应答——》"+strs[1];
             }else {
                 result=   name+"无效请求";
             }
             client.write(ByteBuffer.wrap(result.getBytes()));
             System.out.println(result);
             //注册写事件
             client.register(selector,SelectionKey.OP_READ);
         }
     }catch (Exception e){

     }


    }

    public static void main(String[] args) {
        try {
            new NioServer(8888,"【Server_NIO】").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
