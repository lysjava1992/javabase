package com.handbook.java.jvm;

/**
 *  javac JavaDemo.class
 *  javap -v JavaDemo.class>JavaDemo.txt
 * @ClassName JavaDemo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/28 8:42
 * @Version 1.0
 **/
public class JavaDemo {
    public static void main(String[] args) {
        int x=500;
        int y=100;
        int a=x/y;
        int b=50;
        System.out.println(a+b);
    }

}
