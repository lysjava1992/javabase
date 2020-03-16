package com.handbook.java.lambda.service;

/**
 * @description:
 *  lambda表达式简化了匿名内部类的开发
 *   此接口只能有一个方法
 *
 *   @FunctionalInterface
 *     函数式接口 仅且只有一个接口方法
 * @author: Mr.Luan
 * @create: 2019-12-10 10:38
 **/
@FunctionalInterface
public interface DoSome4 {
     String method3(int a ,int b);
}
