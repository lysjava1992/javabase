package com.learn.io.tradition;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class IoClient {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new Thread(()->{
                try {
                    Socket socket=new Socket("127.0.0.1",8633);
                    while (true){
                        OutputStream os= socket.getOutputStream();
                        os.write(("时间 :["+System.currentTimeMillis()+"]").getBytes());
                        os.flush();
                        Thread.sleep(2000);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }

    }
}
