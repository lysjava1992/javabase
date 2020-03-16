package com.handbook.java.lambda;

import java.util.*;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-12-10 09:48
 **/
public class Chapter2 {
    public static void main(String[] args) {
        List<String> list= Arrays.asList("one","two","three","four","five","six");
        for (String s:list){
            System.out.println(s);
        }
        System.out.println("==============");
        list.forEach(s->System.out.println(s));
        System.out.println("==============");
        list.forEach(System.out :: println);

        Map<String,String> map=new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        map.put("key5","value5");
        map.put("key6","value6");
        map.put("key7","value7");
        map.put("key8","value8");

        map.forEach((key,value)->System.out.println(key+"==>"+value));
    }
}
