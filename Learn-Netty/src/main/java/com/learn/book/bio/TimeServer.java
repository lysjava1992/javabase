package com.learn.book.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server=null;
        try {
            server=new ServerSocket(8011);
            System.out.println("Server Listening Port ...."+server.getLocalPort());
            Socket socket=null;
            while (true){
                socket=server.accept();
                new Thread(new TimeServerHandler(socket)).start();
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
