package com.handbook.java.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *         配置：
 *          -Xmx20M -XX:MaxDirectMemorySize=10M
 *    直接内存（Direct Memory）并不是虚拟机运行时数据区的一部分，也不是Java虚拟机规范中定义的内存区域。
 * @ClassName DirectMemoryOOM
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/2 19:48
 * @Version 1.0
 **/
public class DirectMemoryOOM {
    private static final int _1MB=1024*1024;

    /**
     * java.lang.OutOfMemoryError
     * @param args
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeFiled=Unsafe.class.getDeclaredFields()[0];
        unsafeFiled.setAccessible(true);
        //Java中的Unsafe类为我们提供了类似C++手动管理内存的能力
        Unsafe unsafe= (Unsafe) unsafeFiled.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
