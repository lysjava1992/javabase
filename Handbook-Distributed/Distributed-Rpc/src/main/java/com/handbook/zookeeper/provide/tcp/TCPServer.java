package com.handbook.zookeeper.provide.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName TCPServer
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 10:59
 * @Version 1.0
 **/
public class TCPServer {
    private int port;
    public TCPServer(String ip) {
        String[] arr=ip.split(":");
        if(arr.length==2){
        this.port=Integer.parseInt(arr[1]);
        }else {
            throw new RuntimeException("配置错误 ："+ip);
        }
    }

    /**
     * 启动socket服务
     * @param map
     */
    public void start(Map<String, Object> map) {
        ServerSocket serverSocket=null;
        ExecutorService exec= Executors.newCachedThreadPool();
        try {
          serverSocket =new ServerSocket(port);
            System.out.println("==================等待客户端连接==================");
          while (true){
              Socket socket=serverSocket.accept();
              exec.execute(new RpcSocketHandler(socket,map));
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
