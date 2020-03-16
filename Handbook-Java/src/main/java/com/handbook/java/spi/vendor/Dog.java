package com.handbook.java.spi.vendor;

import com.handbook.java.spi.service.Animal;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-11-05 16:14
 **/
public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("狗粮。。。。");
    }


    @Override
    public void run() {
        System.out.println("跑。。。。。。。。");
    }
}
