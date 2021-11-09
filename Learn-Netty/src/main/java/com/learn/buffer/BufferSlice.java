package com.learn.buffer;

import java.nio.IntBuffer;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *  缓冲区切片
 *   分片
 *     子分片变化，父分片也变化
 * @Version 1.0
 **/
public class BufferSlice {
    public static void main(String[] args) {
        IntBuffer buffer=IntBuffer.allocate(10);
        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.put(i);
        }

        //设置分片 起始位置下标
        buffer.position(3);
        //设置分片 结束位置下标
        buffer.limit(7);
        // 含头不含尾
        IntBuffer sonBuffer=buffer.slice();

        //修改子分片内容
        for (int i = 0; i < sonBuffer.capacity(); i++) {
            sonBuffer.put(i,sonBuffer.get(i)*10);
        }

        //输出
        System.out.println("子分片");
        while (sonBuffer.remaining()>0){
            System.out.print(sonBuffer.get()+"  ");
        }
        System.out.println();
        System.out.println("父分片");
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining()>0){
            System.out.print(buffer.get()+"  ");
        }
    }
}
