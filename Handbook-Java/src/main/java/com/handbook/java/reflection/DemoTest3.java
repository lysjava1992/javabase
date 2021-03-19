package com.handbook.java.reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 私有变量
 * 私有方法
 */
public class DemoTest3 {
    Class aClass=MyObject.class;

    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
         // 在父类中定义的成员变量获取不到
         // private | public | final |protected
        Field[] privateFields=aClass.getDeclaredFields();
        for (Field privateField : privateFields) {
            System.out.println(privateField);
        }
        MyObject object= (MyObject) aClass.newInstance();
        object.setName("Tom");
        Field privateField=aClass.getDeclaredField("name");
        privateField.setAccessible(true);
       System.out.println(privateField.get(object));
    }

    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // 在父类中定义的方法获取不到
        // private | public | final |protected
        Method[] methods=aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        Method method=aClass.getDeclaredMethod("test2");
        MyObject object= (MyObject) aClass.newInstance();
        method.setAccessible(true);
        method.invoke(object);

    }
}
