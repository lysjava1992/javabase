package com.handbook.java.jdk8.chapter3;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *       Lambda表达式 闭包，
 *          允许把函数作为一个方法的参数，使代码变得更加简洁紧凑
 *          (parameters) -> expression
 *            或
 *          (parameters) ->{ statements; }
 *       可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 *       可选的参数园括号：一个参数无需定义园括号，但多个参数需要定义园括号
 *       可选的大括号：如果主体只包含了一个语句，就不需要使用大括号
 *       可选返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回一个数值
 *
 *       Lambda表达式使匿名函数的使用更加简洁优雅
 * @ClassName LambdaApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 9:51
 * @Version 1.0
 **/
public class LambdaApp {
    public static void main(String[] args) {
       Hello1 hello1=()->System.out.println("Hello1");
       hello1.hello();
       Hello2 hello2=msg->System.out.println(msg);
       hello2.hello("Hello2");
       Hello3 hello3=(msg1, msg2) ->{System.out.println(msg1);System.out.println(msg2);} ;
       hello3.hello("Hello3","Hello3");
       Hello4 hello4=()->4;
       System.out.println(hello4.hello());
       Hello5 hello5=(a,b)->{a++;b++;return a+b;};
       System.out.println(hello5.hello(1,1));

       new Thread(()->System.out.println("线程=====")).start();
    }
    interface Hello1{
       void hello();
    }
    interface Hello2{
        void hello(String msg);
    }
    interface Hello3{
        void hello(String msg1,String msg2);
    }
    interface Hello4{
        int hello();
    }
    interface Hello5{
        int hello(int a,int b);
    }
}
