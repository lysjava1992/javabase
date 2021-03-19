package com.learn.book.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable{
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile  boolean stop;

    /**
     *  初始化 多路复用器
     *  绑定监听端口
     * @param port
     */
    public MultiplexerTimeServer(int port) {

        try {
             selector=Selector.open();
             serverChannel=ServerSocketChannel.open();
             serverChannel.configureBlocking(false);
             serverChannel.socket().bind(new InetSocketAddress(port),1024);
             serverChannel.register(selector, SelectionKey.OP_ACCEPT);
             System.out.println("Server Is Listening Port ..."+port);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void  stop(){
        this.stop=true;
    }
    @Override
    public void run() {
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys=selector.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeys.iterator();
                SelectionKey key=null;
                while (iterator.hasNext()){
                    key=iterator.next();
                    iterator.remove();
                    handleInput(key);
                }
            }catch (Exception e){

            }
        }
     if(selector!=null){
         try {
             selector.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
    }

    private void handleInput(SelectionKey key) throws Exception {
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc= (ServerSocketChannel) key.channel();
                SocketChannel sc=ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                SocketChannel sc= (SocketChannel) key.channel();
                ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                int len=sc.read(byteBuffer);
                if(len>0){
                    byteBuffer.flip();
                   String request=new String(byteBuffer.array(),  0,len);
                   System.out.println("请求 "+request);
                   doWrite(sc);
                }else if(len<0){
                    key.cancel();
                    sc.close();
                }else { }
            }


        }
    }

    private void doWrite(SocketChannel channel) throws Exception {
        Thread.sleep(5000);
        ByteBuffer byteBuffer=ByteBuffer.wrap((System.currentTimeMillis()+"").getBytes());
        channel.write(byteBuffer);
    }


}
