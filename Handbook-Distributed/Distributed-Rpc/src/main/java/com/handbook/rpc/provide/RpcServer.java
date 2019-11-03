package com.handbook.rpc.provide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RpcServer
 *  负责发布服务
 *    Socket服务端 维护
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 17:18
 * @Version 1.0
 **/
public class RpcServer {
    final ExecutorService executorService= Executors.newCachedThreadPool();
    public void publish(int  port ,Object service){
        ServerSocket serverSocket=null;
        try {
             serverSocket=new ServerSocket(port);
             System.out.println("服务器启动");
            while (true){
                Socket socket=serverSocket.accept();
               executorService.execute(new RpcHandler(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
