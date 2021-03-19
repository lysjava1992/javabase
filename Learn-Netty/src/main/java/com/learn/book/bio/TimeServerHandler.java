package com.learn.book.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
            String question=reader.readLine();
            while (question!=null){
                String answer = getTime(question);
                writer.println(answer);
                question = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getTime(String question) {
        System.out.println("请求 ："+question);
        String result=System.currentTimeMillis()+"";
        try {
         //   Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
