package com.learn.io.tradition;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * serverSelector   连接请求选择器
 * clientSelector    读写
 */
public class NIoServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector=Selector.open();
        Selector clientSelector=Selector.open();

        //处理连接
        new Thread(()->{
            try {
                ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
                serverSocketChannel.socket().bind(new InetSocketAddress(8633));
                //非阻塞
                serverSocketChannel.configureBlocking(false);
                //SelectionKey.OP_ACCEPT 连接可接受
                serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
                while (true){
                    if(serverSelector.select(1)>0){
                        Set<SelectionKey> selectionKeySet=serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator=selectionKeySet.iterator();;
                        while (keyIterator.hasNext()){
                            SelectionKey key=keyIterator.next();
                            if(key.isAcceptable()){
                                try {
                                    SocketChannel clientChannel=((ServerSocketChannel)(key.channel())).accept();
                                    clientChannel.configureBlocking(false);
                                    //注册到选择器
                                    clientChannel.register(clientSelector,SelectionKey.OP_READ);
                                }finally {
                                    keyIterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        //处理读写
        new Thread(()->{
            try {
                while (true){
                    if(clientSelector.select(1)>0){
                          Set<SelectionKey> set= clientSelector.selectedKeys();
                          Iterator<SelectionKey> selectionKeyIterator=set.iterator();
                          while (selectionKeyIterator.hasNext()){
                              SelectionKey key=selectionKeyIterator.next();
                              if(key.isReadable()){
                                  SocketChannel socketChannel= (SocketChannel) key.channel();
                                  ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                                  socketChannel.read(byteBuffer);
                                  byteBuffer.flip();
                                System.out.println("接收消息：["+ Charset.defaultCharset().newDecoder().decode(byteBuffer).toString()+"]");
                                //处理完毕 删除
                                selectionKeyIterator.remove();
                                key.interestOps(SelectionKey.OP_READ);
                              }
                          }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
