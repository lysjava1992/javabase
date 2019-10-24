package com.handbook.socket.bio;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: Socket编程  通常来说客户端使用Bio即可
 *   BIO 同步阻塞IO
 *   JDK原生API 简单实现
 *   客户端向 implements Runnable
 *         实现Runnable接口是为了方便多客户端测试
 * socket.getOutputStream()      字节输出流
 * socket.getInputStream()       字节输入流
 * @author: Mr.Luan
 * @create: 2019-10-24 08:59
 **/
public class BioClient implements Runnable{
      private String ip;
      private int port;
      private String name;
      private Socket socket;
    public BioClient(String ip, int port,String name) throws IOException {
        this.ip = ip;
        this.port = port;
        this.socket=new Socket(this.ip,this.port);
        this.name=name;
    }
    public void run() {
        try {
            InputStream is=socket.getInputStream();
            OutputStream os=socket.getOutputStream();
            os.write((name+"请求").getBytes());
            os.flush();
            byte[] arr=new byte[1024];
            int len=0;
              len=is.read(arr);
            System.out.println(new String(arr,0,len));
            os.close();
            is.close();
           socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        ExecutorService singlePool = Executors.newCachedThreadPool();
        for(int i=0;i<1;i++){
            Thread thread=new Thread(new BioClient("127.0.0.1",8888,"【Client_Bio_"+i+"】->"));
            singlePool.execute(thread);
        }

  //    new BioClient("127.0.0.1",8888,"【Client_Bio_1】->").run();
    }
}
