package com.handbook.java.jdk8.chapter5;

import java.util.Arrays;
import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *        方法引用通过方法的名字来指向一个方法。
 *        方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 *        方法引用使用一对冒号 ::
 * @ClassName CarApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 10:56
 * @Version 1.0
 **/
public class CarApp {
    public static void main(String[] args) {
         //构造器引用
        Car car=Car.create(Car::new);
        List<Car> carList= Arrays.asList(car);

        //静态方法引用 Class::static_method
        // forEach(Consumer<? super T> action) 代表了接受一个输入参数并且无返回的操作
        carList.forEach(Car::collide);

        //特定类的任意方法引用 Class::method
        carList.forEach(Car::repair);

        //特定对象的方法引用 instance::method
        Car police=Car.create(Car::new);
        carList.forEach(police::follow);
    }
}
