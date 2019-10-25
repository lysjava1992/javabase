package com.handbook.chat.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * 基于jdk 原生io的 聊天室案例
 *   服务端
 *    1.接收客户端连接 发送消息建昵称
 *    2.维护客户端列表 即昵称列表
 *    3.接收客户端消息 推送给所有的客户端
 *
 *  相当于群聊功能
 * @author: Mr.Luan
 * @create: 2019-10-25 11:30
 **/
public class BioChatServer {
    private int port ;
    private final  Map<ClientChannle,String> map=new HashMap<>();
    public BioChatServer(int port) {
        this.port = port;
    }
    private void start() {
        try {
            ServerSocket server=new ServerSocket(port);

            while (true){
                Socket socket=server.accept();
                ClientChannle channle=new  ClientChannle(socket);
                new Thread(channle).start();
               map.put(channle,"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   class ClientChannle implements Runnable{
       private Socket socket;
       private InputStream is;
       private OutputStream os;
       public ClientChannle(Socket socket) {
           this.socket = socket;
           try {
               this.is=socket.getInputStream();
               this.os=socket.getOutputStream();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

       @Override
       public void run() {
           while (true){
               try {
                   byte[] bytes=new byte[1024];
                   int len=is.read(bytes);
                   if(len==-1){
                       System.out.println(map.get(this)+":  下线");
                       this.socket.close();
                       String response="SYSTEM@"+map.get(this)+"下上线";
                       map.remove(this);
                       broadcast(response,true);
                       break;
                       }
                   String msg=new String(bytes,0,len);
                   String[] request=msg.split("@");
                   if(request.length==2){
                        if(request[0].equals("LOGIN")){
                            //注册
                            os.write(checkName(request[1]).getBytes());
                        }else {
                            //广播消息
                            broadcast(msg,true);
                        }
                   }
               } catch (IOException e) {
                   try {
                       this.socket.close();
                   } catch (IOException e1) {
                       e1.printStackTrace();
                   }
                   System.out.println(map.get(this)+":  下线");
                   String response="SYSTEM@"+map.get(this)+"下上线";
                   map.remove(this);
                   broadcast(response,true);
                   break;
               }
           }
       }

       private String checkName(String name) {
           for(String value : map.values()){
             if(name.equals(value)){ return "ERR"; }
           }
           map.put(this,name);
           String response="SYSTEM@"+name+"已上线";
           broadcast(response,false);
           return "OK";
       }
       private void sendMsg(String msg){
           try {
               os.write(msg.getBytes());
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       private void broadcast(String request,boolean state) {
           if(state){
               for (ClientChannle channle:map.keySet()){
                   channle.sendMsg(request);
               }
           }else {
               for (ClientChannle channle:map.keySet()){
                   if(channle==this){
                       continue;
                   }
                   channle.sendMsg(request);
               }
           }

       }
   }

    public static void main(String[] args) {
        new BioChatServer(8888).start();
    }
}
