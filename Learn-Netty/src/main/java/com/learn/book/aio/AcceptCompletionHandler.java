package com.learn.book.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 负责客户端接入
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {


    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        //再有客户端接入
        // accept继续调用    this.completed方法 形成一个 while(true){socket.accept()}
        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        result.read(byteBuffer,byteBuffer,new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
     exc.printStackTrace();
     attachment.latch.countDown();
    }
}
