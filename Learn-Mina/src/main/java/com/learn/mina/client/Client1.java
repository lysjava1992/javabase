package com.learn.mina.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client1 {

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",8088);
        InputStream is=socket.getInputStream();
        OutputStream os=socket.getOutputStream();
        os.write("quit\n".getBytes());
        os.flush();
        byte[] arr=new byte[1024];
        int len=is.read(arr);
        System.out.println(new String(arr,0,len));
        is.close();
        os.close();
    }
}
