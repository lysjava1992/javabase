package com.handbook.socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: Mr.Luan
 *  BIO server 一个请求一个请求处理
 *     对服务器来说效率低下
 *     可以使用多线程来处理
 *  服务器在读取客户端时
 * @create: 2019-10-24 09:19
 **/
public class BioServer {
    private int port;
    private String name;
    private ServerSocket server;
    public BioServer(int port, String name) throws IOException {
        this.port = port;
        this.name = name;
        this.server=new ServerSocket(this.port);
    }
    private void startAlone() {

        try {
            System.out.println("等待连接【"+this.port+"】");
            while (true){
                //阻塞在此
                Socket socket=server.accept();
                InputStream is=socket.getInputStream();
                OutputStream ot=socket.getOutputStream();
                byte[] arr=new byte[1024];
                int len=is.read(arr);

                String request=new String(arr,0,len);
                System.out.println(request);
                String[] strs=request.split("->");
                String name=this.name;
                if(strs.length==2){
                   ot.write((name+"应答——》"+strs[0]).getBytes());
                }else {
                    ot.write((name+"无效请求").getBytes());
                }
                ot.flush();
                ot.close();
                is.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new BioServer(8888,"【Server_Bio_Alone】").startAlone();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
