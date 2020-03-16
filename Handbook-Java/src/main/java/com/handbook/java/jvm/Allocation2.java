package com.handbook.java.jvm;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *        新对象内存分配
 *
 *        配置： -Xms20m -Xmx20m -Xmn10m -verbose:gc -XX:+PrintGCDetails -XX:PretenureSizeThreshold=3M
 *
 *                  gc 日志   堆20M 新生代10M（8:1:1）
 *                   大对象
 * @ClassName Allocation1
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/3 19:57
 * @Version 1.0
 **/
public class Allocation2 {
    public static void main(String[] args) {
        byte[] bigSize1=new byte[1024*1024*4];

    }
}
