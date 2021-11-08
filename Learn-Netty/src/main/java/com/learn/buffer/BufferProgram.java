package com.learn.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName BufferProgram
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/11/7 11:48
 * @Version 1.0
 **/
public class BufferProgram {
    public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream("F:/desktop/io.txt");
        FileChannel fc=fis.getChannel();

        ByteBuffer buffer=ByteBuffer.allocate(8);
        outPut("初始状态",buffer);

        //读取
        fc.read(buffer);
        outPut("调用read()读取完毕",buffer);

        //修改状态取
        buffer.flip();
        outPut("调用read()修改状态",buffer);
        // 取
        while (buffer.remaining()>0){
            byte b=buffer.get();
            System.out.print(((char)b));
        }
         System.out.println();
        outPut("调用get()取出完毕",buffer);

        //重新初始化
        buffer.clear();
        outPut("调用clear()清空",buffer);

        //关闭
        fis.close();
    }

    private static void outPut(String step, ByteBuffer buffer) {
        System.out.println("操作------------:"+ step);
        //容量 数据大小
        System.out.print("容量[capacity]: "+buffer.capacity()+",");
        //游标
        System.out.print("游标[position]: "+buffer.position()+",");
        //锁定值 flip
        System.out.println("可操作[limit]: "+buffer.limit());
    }
}
