package com.handbook.chat.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName NioChatServer
 *  基于原生jdk nio 的一个聊天室服务器 与bio客户端使用
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/10/26 12:06
 * @Version 1.0
 **/
public class NioChatServer {
    private int port;
    private ServerSocketChannel server;
    private Selector selector;
    private Set<String > map=new HashSet<>();
    private Map<SocketChannel ,String> cache=new HashMap<>();
    private Charset charset=Charset.forName("UTF-8");
    public NioChatServer(int port) {
        this.port = port;
        try {
            server=ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            selector=Selector.open();
            //注册监听事件
            server.register(selector,SelectionKey.OP_ACCEPT);
            listening();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void listening() {
        while (true){
            try {
                if(selector.select()<=0){continue;}
                Iterator <SelectionKey>iterator=selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key=iterator.next();
                    iterator.remove();
                    process(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void process(SelectionKey key) throws IOException {

        ByteBuffer buffer=ByteBuffer.allocate(1024);
        if (key.isAcceptable()){
            SocketChannel socketChannel= server.accept();
            //连接事件
            socketChannel.configureBlocking(false);
            //注册读
            socketChannel.register(selector,SelectionKey.OP_READ);

        }else if(key.isReadable()){
            SocketChannel socketChannel= (SocketChannel) key.channel();
            StringBuilder content=new StringBuilder();
            while (socketChannel.read(buffer)>0){
                buffer.flip();
                content.append(charset.decode(buffer));
                buffer.clear();
            }
            buffer.clear();
            String msg=content.toString();
            String[] request=msg.split("@");
            if(request.length==2){
                if(request[0].equals("LOGIN")){
                    //注册
                    String result=checkName(request[1],socketChannel);
                    //返回
                    socketChannel.write(ByteBuffer.wrap(result.getBytes())) ;
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else {
                    //广播消息
                    broadcast(msg);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
            }
        }
    }
    private String checkName(String name, SocketChannel socketChannel) throws IOException {
        if(map.contains(name)){
            return "ERR";
        }
        map.add(name);
        cache.put(socketChannel,name);
        String response="SYSTEM@"+name+"已上线";
        broadcast(response,socketChannel);
        return "OK";
    }

    private void broadcast(String request,SocketChannel client) throws IOException {
        //广播
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            //如果client不为空，不会发给发送此消息的客户端
            if (targetChannel instanceof SocketChannel&& targetChannel!=client ) {
                SocketChannel target = (SocketChannel) targetChannel;
                target.write(charset.encode(request));
            }

        }
    }
    private void broadcast(String request) throws IOException {
        //广播
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            //如果client不为空，不会发给发送此消息的客户端
            if (targetChannel instanceof SocketChannel ) {
                SocketChannel target = (SocketChannel) targetChannel;
                target.write(charset.encode(request));
            }

        }
    }

    public static void main(String[] args) throws IOException {
     new NioChatServer(8888).listening();
    }
}
