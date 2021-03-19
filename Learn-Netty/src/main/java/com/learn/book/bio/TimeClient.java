package com.learn.book.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            //创建一个Socket并连接到指定的目标服务器
            socket = new Socket("localhost", 8011);
            reader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            while (true){
                writer.println("Time Request");
                writer.flush();
                System.out.println("发送请求.....");
                String answer = reader.readLine();
                System.out.println("NOW :" + answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }

                if (reader != null) {
                    reader.close();
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
