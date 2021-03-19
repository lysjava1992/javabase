package com.learn.book.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandle implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public TimeClientHandle(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop) {
            try {
                selector.select(1001);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    handleInput(key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            SocketChannel sc= (SocketChannel) key.channel();
            if(key.isConnectable()){
                 if(sc.finishConnect()){
                     sc.register(selector,SelectionKey.OP_READ);
                     doWrite(sc);
                 }else {
                     System.exit(1);
                 }
            }
            if(key.isReadable()){
                ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                int len=sc.read(byteBuffer);
                if(len>0){
                    byteBuffer.flip();
                    String result=new String(byteBuffer.array(),0,len);
                    System.out.println("返回 "+result);
                    this.stop=true;
                }else  if(len<0){
                    key.cancel();
                    sc.close();
                }else {}
            }
        }
    }

    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(this.host, this.port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {

            ByteBuffer byteBuffer = ByteBuffer.wrap("Time Request".getBytes());
            socketChannel.write(byteBuffer);
            System.out.println("请求");

    }

}
