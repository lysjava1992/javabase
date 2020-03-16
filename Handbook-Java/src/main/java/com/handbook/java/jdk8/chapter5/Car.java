package com.handbook.java.jdk8.chapter5;

import java.util.function.Supplier;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName car
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 10:53
 * @Version 1.0
 **/
public class Car {

    /**
     *
     * @param supplier  jdk1.8的一个函数式接口 Supplier<T>无参数，返回一个结果。
     * @return
     */
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    /**
     * 碰撞
     * @param car
     */
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    /**
     * 跟随
     * @param another
     */
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    /**
     *修理
     */
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}
