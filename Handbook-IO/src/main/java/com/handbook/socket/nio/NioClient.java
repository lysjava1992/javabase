package com.handbook.socket.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-24 13:32
 **/
public class NioClient {
    private int port;
    private String ip;
    private SocketChannel client;
    private Selector selector;
    private String name;
    private Charset charset=Charset.forName("UTF-8");
    public NioClient(int port, String ip, String name) {
        this.port = port;
        this.ip = ip;
        this.name = name;
    }

    private void start() {
        try {
            client=SocketChannel.open(new InetSocketAddress(ip,port));
            client.configureBlocking(false);
            selector=Selector.open();
            client.register(selector, SelectionKey.OP_READ);
            listening();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listening() {
    new Thread(new ReadThread()).start();
    new Thread(new WriteThread()).start();
    }
    private class ReadThread implements Runnable{
       @Override
       public void run() {
           System.out.println("输入");
              while (true){
                  try {
                      if(selector.select()==0){continue;}
                    Iterator<SelectionKey> iterator= selector.selectedKeys().iterator();
                      while (iterator.hasNext()){
                          readMag(iterator.next());
                          iterator.remove();
                      }
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
       }
   }

    private void readMag(SelectionKey key) throws IOException {
        if(!key.isReadable()){return;}
        ByteBuffer buffer=ByteBuffer.allocate(1024);
       SocketChannel channel= (SocketChannel) key.channel();
        StringBuilder content=new StringBuilder();
        while (channel.read(buffer)>0){
            buffer.flip();
            content.append(charset.decode(buffer));
            buffer.clear();
        }
        System.out.println(content.toString());
        key.interestOps(SelectionKey.OP_READ);
    }

    private class WriteThread implements Runnable{

        @Override
        public void run() {
            Scanner scanner=new Scanner(System.in);
            while (scanner.hasNextLine()){
                String line=scanner.nextLine();
                if("".endsWith(line)){continue;}
                try {
                    client.write(charset.encode(name+line));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            scanner.close();
        }
    }
    public static void main(String[] args) {
            new NioClient(8888,"127.0.0.1","【Client_NIO_1】->").start();
    }

}
