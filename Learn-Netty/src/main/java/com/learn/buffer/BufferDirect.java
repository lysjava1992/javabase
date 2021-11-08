package com.learn.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 * <p>
 * 直接缓冲区
 * 读写大文件时具有优势
 *
 * @Author Mr.Luan
 * @Date 2021/11/7 12:19
 * @Version 1.0
 **/
public class BufferDirect {
    public static void main(String[] args) throws IOException {
        String targetPath = "F:desktop/01.ev4";
        String newPath0 = "F:desktop/01_0.ev4";
        String newPath1 = "F:desktop/01_1.ev4";
        commonCopy(targetPath, newPath0);
        directCopy(targetPath, newPath1);
    }

    private static void commonCopy(String targetPath, String newPath) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] bytes = new byte[1024];
        try {
            fis = new FileInputStream(targetPath);
            fos = new FileOutputStream(newPath);
            int count = fis.read(bytes);
            while (count > 0) {
                fos.write(bytes, 0, count);
                count = fis.read(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
        System.out.println("普通耗时： " + (System.currentTimeMillis() - start));

    }

    private static void directCopy(String targetPath, String newPath) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel readChannel = null;
        FileChannel writeChannel = null;
        // 直接缓冲区
        ByteBuffer buffer=ByteBuffer.allocateDirect(1024);
        try {
            fis = new FileInputStream(targetPath);
            fos = new FileOutputStream(newPath);
            readChannel = fis.getChannel();
            writeChannel = fos.getChannel();
            int len= readChannel.read(buffer);
            while (len>0){
                buffer.flip();
                writeChannel.write(buffer,len);
                buffer.clear();
                len=readChannel.read(buffer);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (readChannel != null) {
                readChannel.close();
            }
            if (writeChannel != null) {
                writeChannel.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
        System.out.println("直接耗时： " + (System.currentTimeMillis() - start));
    }
}
