package com.learn.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *  映射缓冲区
 * @Author Mr.Luan
 * @Date 2021/11/7 12:44
 * @Version 1.0
 **/
public class BufferMap {
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile file=new RandomAccessFile("F:/desktop/io.txt","rw");
        FileChannel channel=file.getChannel();
        MappedByteBuffer byteBuffer=channel.map(FileChannel.MapMode.READ_WRITE,0,10);

        byteBuffer.put(2, (byte) 97);
        // 缓冲区的修改会直接修改文件
        Thread.sleep(10000);
        channel.close();
        file.close();
    }
}
