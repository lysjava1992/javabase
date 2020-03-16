package com.learn.spring.base.chapter7;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName LogMethod
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 15:46
 * @Version 1.0
 **/
public class LogMethod {
    public void before(){
        System.out.println("执行前准备...........");
    }
    public void after(){
        System.out.println("执行后收尾...........");
    }

    /**
     * 环绕方法
     *  point.proceed(); 控制执行顺序
     * @param point  切点
     * @throws Throwable
     */
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕前...........");
        point.proceed();
        System.out.println("环绕后...........");
    }
}
