package com.handbook.java.jdk8.ssy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        Test3Interface1 interface1=()->{};
        System.out.println(interface1.getClass().getInterfaces()[0]);
        Test3Interface2 interface12=()->{};
        System.out.println(interface12.getClass().getInterfaces()[0]);
        new Thread(()->{System.out.println("hello");}).start();
        List<String> list= Arrays.asList("a","hello","b","world");
       //  list.forEach(item->System.out.println(item.toUpperCase()));
        List<String> list2=new ArrayList<>();
        list.forEach(item->list2.add(item.toUpperCase()));
        list2.forEach(item->System.out.println(item.toUpperCase()));

        list.stream().map(item->item.toUpperCase()).forEach(item->System.out.println(item));
        list.stream().map(String::toUpperCase).forEach(System.out::println);
    }
}
@FunctionalInterface
interface Test3Interface1{
    void method();
}


@FunctionalInterface
interface Test3Interface2{
    void method();
}
