package com.handbook.java.lambda;

import com.handbook.java.lambda.service.DoSome;
import com.handbook.java.lambda.service.DoSome2;
import com.handbook.java.lambda.service.DoSome3;
import com.handbook.java.lambda.service.DoSome4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 **
 * @author: Mr.Luan
 * @create: 2019-12-10 09:56
 **/
public class Chapter3 {

    private static  void method3(String s){
        System.out.println(s+"====接口");
    }

    private  static  String method4(int a ,int b){
        return a+"_"+b;
    }
    public static void main(String[] args) {

        DoSome doSome1=()->System.out.println("DoSome====接口");
        doSome1.method1();

        DoSome2 doSome2=str ->System.out.println(str+"====接口");
        doSome2.method2("DoSome2");


        /**
         * 用Lambda 来实现匿名内部类，有时候其实现的方法仅仅是一个已经实现的方法
         * 则可直接引用方法来实现
         * 前提接口的方法 和被调用的方法 参数 返回值类型一直
         */

        DoSome2 doSome21=Chapter3::method3;
        doSome21.method2("方法引用");

        DoSome2 doSome22=System.out::println;
        doSome22.method2("方法引用====接口");

        DoSome3 doSome3=()->{return "OK";};
        System.out.println(doSome3.method3());

        DoSome4 doSome4=(a,b)->{return a+b+"";};

        DoSome4 doSome41=Chapter3::method4;
          doSome4.method3(1,2);
    }



}
