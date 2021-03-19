package com.handbook.java.reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 成员变量 方法
 */
public class DemoTest2 {
    Class aClass=MyObject.class;
    @Test
    public void test1() throws NoSuchFieldException {
        Field[] fields=aClass.getFields();
        for (Field f :fields) {
            System.out.println(f.getName());
        }
        Field field=aClass.getField("fieldOne");
        System.out.println(field.getType());
    }
    @Test
    public void test2() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Field field=aClass.getField("fieldOne");
        MyObject object= (MyObject) aClass.newInstance();
        field.set(object,"Tom");
        String value= (String) field.get(object);
         System.out.println(value);
    }
    @Test
    public void test3(){
        Method[] methods=aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

    }
    @Test
    public void test4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {


        Method method=aClass.getMethod("test1",String.class);
        MyObject object= (MyObject) aClass.newInstance();
        System.out.println(method);
        method.invoke(object,"l");

        Method method4=aClass.getMethod("test1");
        method4.invoke(object);

        System.out.println(method.getReturnType());
        method.invoke(object,"Tom");
        Method method2=aClass.getMethod("test1",String.class,String.class);
        System.out.println(method2);

        //静态方法调用
        Method method3=aClass.getMethod("test3",String.class);
        System.out.println(method3.invoke(null,"Hello"));
    }
}
