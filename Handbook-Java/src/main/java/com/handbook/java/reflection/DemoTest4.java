package com.handbook.java.reflection;

import org.junit.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class DemoTest4 {
    Class aClass=MyClass.class;

    @Test
    public void test() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Method method=aClass.getMethod("getUserList");
        Type returnType = method.getGenericReturnType();
        if(returnType instanceof ParameterizedType){
           ParameterizedType type = (ParameterizedType) returnType;
           Type[] types=type.getActualTypeArguments();
           for (Type type1:types){
               Class typeArgClass= (Class) type1;
               System.out.println("-- "+typeArgClass);
           }
        }

        List<User> list= (List<User>) method.invoke(aClass.newInstance());
        list.forEach(System.out::println);
    }

    @Test
    public void test1(){
        int[] arr= (int[]) Array.newInstance(int.class,3);
        Array.set(arr,0,23);
        Array.set(arr,1,123);
        Array.set(arr,2,323);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test2() throws ClassNotFoundException {
        Class bClass=String[].class;
        System.out.println(bClass);
        Class cClass=Class.forName("[I");
        System.out.println(cClass.getName());
    }
    @Test
    public void test3(){
        String[] strings = new String[3];
        Class stringArrayClass = strings.getClass();
        Class stringArrayComponentType = stringArrayClass.getComponentType();
        System.out.println(stringArrayComponentType);

    }
}












