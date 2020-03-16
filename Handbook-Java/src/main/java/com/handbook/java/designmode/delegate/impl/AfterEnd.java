package com.handbook.java.designmode.delegate.impl;

import com.handbook.java.designmode.delegate.Clerk;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName AfterEnd
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/24 11:00
 * @Version 1.0
 **/
public class AfterEnd implements Clerk {
    @Override
    public void doWork() {
        System.out.println("开始后端编程");
    }
}
