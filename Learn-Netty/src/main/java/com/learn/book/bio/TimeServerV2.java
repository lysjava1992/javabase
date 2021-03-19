package com.learn.book.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServerV2 {
    public static void main(String[] args) throws Exception{
        ServerSocket server=null;
        try {
            server=new ServerSocket(8011);
            System.out.println("Server Listening Port ...."+server.getLocalPort());
            Socket socket=null;
            TimeServerHandlerExecutePool  pool=new TimeServerHandlerExecutePool(50,1000);

            while (true){
                socket=server.accept();
                System.out.println("=================1");
                pool.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {

        }finally {
            if(server!=null){
                server.close();
                server=null;
                System.out.println("Server is Close ....");
            }
        }
    }
}
