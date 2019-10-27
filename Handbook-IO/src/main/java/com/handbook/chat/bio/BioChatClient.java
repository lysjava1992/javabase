package com.handbook.chat.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @description:
 *  基于jdk 原生io的 聊天室案例
 *     客户端有两个线程  读取服务器推送的消息
 *                      向服务器推送消息
 *     客户端每次发送消息需要携带自己的昵称
 * @author: Mr.Luan
 * @create: 2019-10-25 11:27
 **/
public class BioChatClient {
    private int port;
    private String ip;
    private String nickname;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    public BioChatClient(String ip, int port) {
        try {
            this.ip = ip;
            this.port = port;
            this.socket = new Socket(ip, port);
            this.inputStream=socket.getInputStream();
            this.outputStream=socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
            new Thread(new WriteThread()).start();
    }

    class ReadThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    byte[] arr = new byte[1024];
                    int len = inputStream.read(arr);
                    String response=new String(arr, 0, len);
                    String[] result = response.split("@");
                    if (result.length == 2) {
                        if (result[0].equals(nickname)) {
                            System.out.println("You Say: " + result[1]);
                        } else if(result[0].equals("SYSTEM")) {
                            System.out.println("【系统通知】: " + result[1]);

                        }else {
                            System.out.println("【"+result[0]+"】: " + result[1]);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    class WriteThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("已连接;请输入昵称.....");
                while (true) {
                    String login = registerNickname();
                    if ("OK".equals(login)) {
                      System.out.println("【系统消息】:您已进入聊天室");
                        break;
                    }
                    System.out.println("昵称已存在,请重新输入");
                }
                //注册成功 ，开始聊天
                new Thread(new ReadThread()).start();
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) {
                    String msg = nickname + "@" + scanner.nextLine();
                    System.out.println(msg);
                    outputStream.write(msg.getBytes());
                    System.out.println("【系统消息】:已发送,请继续输入....");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private String registerNickname() throws IOException {

            Scanner scanner = new Scanner(System.in);
            nickname = scanner.nextLine();
            outputStream.write(("LOGIN@" + nickname).getBytes());
            byte[] arr = new byte[1024];
            int len = inputStream.read(arr);
            return new String(arr, 0, len);
        }
    }
    public static void main(String[] args) {
        new BioChatClient("127.0.0.1", 8888).start();
    }
}
