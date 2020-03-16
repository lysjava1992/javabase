package com.handbook.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *          堆溢出
 *    配置： 堆最大/最小20m     导出异常文件
 *     -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 *
 * @ClassName HeapOOM
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/2 18:15
 * @Version 1.0
 **/
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list=new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
        /**
         * java.lang.OutOfMemoryError: Java heap space
         * Dumping heap to java_pid11808.hprof ... （文件名称 二进制文件 jdk自带jvisualvm打开）
         * Heap dump file created [28224231 bytes in 0.078 secs]
         */
    }
}
