package com.handbook.java.reflection;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MyObject
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/18 20:55
 * @Version 1.0
 **/
@Data
public  class  MyObject {
     private String name;
     public String fieldOne;
     final String fieldTwo="fieldTwo";
     protected static String fieldThree="fieldThree";

    public MyObject(String name) {
        this.name = name;
    }

    public MyObject() {

    }

    public void test1(){
        System.out.println("无参函数test()");
    }
    public void test1(String name){
        System.out.println("1个参函数["+name+"]");
    }
    public void test1(String name,String addr){
        System.out.println("2个参函数["+name+" | "+addr+"]");
    }

    private void test2(){}

    public String  test3(){
        return "OK";
    }
    public static String  test3(String name){
        return name;
    }
}
