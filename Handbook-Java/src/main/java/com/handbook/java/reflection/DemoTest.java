package com.handbook.java.reflection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Demo1
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/18 21:05
 * @Version 1.0
 **/
public class DemoTest {

    /**
     * 获取Class
     * @throws
     */
    @Test
    public void test1() throws ClassNotFoundException {
        //编译期知道类名
        Class myObjectClass=MyObject.class;

        //运行期获取类名
        //全路径名
        Class myObjectClass2=Class.forName("com.handbook.java.reflection.MyObject");
        System.out.println(myObjectClass==myObjectClass2);

        //全限定名
        System.out.println(myObjectClass.getName());
        //无包名
        System.out.println(myObjectClass2.getSimpleName());

        int modifiers=myObjectClass.getModifiers();
        System.out.println(Modifier.isPublic(modifiers));
        System.out.println(Modifier.isFinal(modifiers));


        Package pack=myObjectClass.getPackage();
        System.out.println(pack.getName());

    }
    @Test
    public void test2(){
        Class c=MyObject.class;
        Class parent=c.getSuperclass();
        System.out.println(parent.getSimpleName());

        Class[] interfaces=c.getInterfaces();
        System.out.println(interfaces.length);
    }

    @Test
    public void test3(){
        Class c=MyObject.class;


        Method[] methods=c.getMethods();
        for (Method method : methods) {
            System.out.println("method： "+method);
        }
    }
    @Test
    public void test4(){
        Class c=MyObject.class;
         Field[] fields=  c.getFields();
         System.out.println(fields.length);
        for (Field field : fields) {
            System.out.println(field);
        }
        Annotation[] annotations = c.getAnnotations();
        System.out.println(annotations.length);
    }
}
