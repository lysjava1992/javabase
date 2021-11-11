package com.learn.aviator.hello;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.io.IOException;

/**
 *  AviatorEvaluator 取脚本并动态实时地编译成 JVM 字节码，最终的结果是一个 Expression 对象
 *   每次调用 compileScript(path) 都生成一个新的匿名类和对象，
 *   因此如果频繁调用会占满 JVM 的 metaspace，可能导致 full gc 或者 OOM（
 *   因此还有一个方法 compileScript(path, cached) 可以通过第二个布尔值参数决定是否缓存该编译结果。
 */
public class HelloApp {
    public static void main(String[] args) throws IOException {
        // 文件加载
        Expression exp= AviatorEvaluator.getInstance().compileScript("hello/hello.av");
        exp.execute();
        // 表达式
        exp=AviatorEvaluator.getInstance().compile("println('Hello,AviatorScript!')");
        exp.execute();

         // 参数
        String expStr="a*(b-c)";
        exp=AviatorEvaluator.compile(expStr,true);
        System.out.println(exp.execute(exp.newEnv("a",5.1,"b",4,"c",12)));
        System.out.println(exp.execute(exp.newEnv("a",5,"b",4,"c",1)));

       //脚本语法校验
        AviatorEvaluator.validate("1 +* 2");
    }

}
