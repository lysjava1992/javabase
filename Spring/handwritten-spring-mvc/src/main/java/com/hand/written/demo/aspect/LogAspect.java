package com.hand.written.demo.aspect;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName LogAspect
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/10 16:12
 * @Version 1.0
 **/
public class LogAspect {
    public void before(){
    System.out.println("前置增强器<-<-<-<-<-<-<-<-<-<-<-");
    }
    public void after(){
    System.out.println("后置增强器->->->->->->->->->->->");
    }
}
