package com.handbook.java.vendor;

import com.handbook.java.service.Animal;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-11-05 16:16
 **/
public class Cat implements Animal {

    @Override
    public void eat() {
        System.out.println("猫粮。。。。。。。。");
    }

    @Override
    public void run() {
        System.out.println("蹦。。。。。。。。");
    }
}
