package com.learn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8011);
            while (true) {
                Socket socket = server.accept();
                new Thread(new ClientSocket(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ClientSocket implements Runnable {
        private Socket socket;

        public ClientSocket(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                byte[] bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes)) > 0) {
                    String msg = new String(bytes);
                    System.err.println("接收： " + msg);
                    os.write(msg.getBytes());
                    os.flush();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
