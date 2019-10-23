package com.handbook.io;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @description: 管道通信 线程之间的信息传递
 * 基于字符流
 *  PipedReader
 *  PipedWriter
 * @author: Mr.Luan
 * @create: 2019-10-23 15:46
 **/
public class StreamThread {
    public static void main(String[] args) throws IOException {
        PipedReader pr =new PipedReader();
        PipedWriter pw=new PipedWriter();
        //通道连接
        pw.connect(pr);
        new WriterThread(pw).run();
        new ReadThread(pr).run();

    }
    static class ReadThread implements Runnable{
        private PipedReader pr;

        public ReadThread(PipedReader pr) {
            this.pr = pr;
        }

        public void run() {
                try {
                    char[] arr=new char[1024];
                   int len= pr.read(arr);
                    pr.close();
                    System.out.println(new String(arr,0,len));
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
    static class  WriterThread implements Runnable{
        private PipedWriter pw;
        public WriterThread(PipedWriter pw) {
            this.pw = pw;
        }
        public void run() {
            try {
                pw.write("ReadThread msg:--------");
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
