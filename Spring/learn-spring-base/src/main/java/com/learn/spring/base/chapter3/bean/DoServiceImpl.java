package com.learn.spring.base.chapter3.bean;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName DoServiceImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 20:18
 * @Version 1.0
 **/
public class DoServiceImpl implements DoService {
    public String doWork1() {
        return "方法一";
    }

    public String doWork2() {
       return "方法二";
    }
}
