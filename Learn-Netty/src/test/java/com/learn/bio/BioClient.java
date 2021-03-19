package com.learn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class BioClient {
    private int port = 8011;
    private String host = "127.0.0.1";
    private Socket socket;

    public void start() {
        try {
            socket = new Socket(host, port);
          //  new Thread(new ClientRead(socket)).start();
            new Thread(new ClientWrite(socket)).start();
        } catch (IOException e) {
            System.err.println("初始化失败");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BioClient().start();
    }

    public static class ClientRead implements Runnable {
        private Socket socket;

        public ClientRead(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                int len;
                byte[] bytes = new byte[1024];
                while ((len = is.read(bytes)) > 0) {
                    String msg = new String(bytes);
                    System.err.println("返回： " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ClientWrite implements Runnable {
        private Socket socket;

        public ClientWrite(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                OutputStream os = socket.getOutputStream();
                while (true) {
                    String msg = UUID.randomUUID() + "";
                    os.write(msg.getBytes());
                    os.flush();
                    Thread.sleep(3000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
