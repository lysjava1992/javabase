package com.handbook.java.designmode.singleton;

import com.handbook.java.designmode.singleton.enumerate.Enumerate;
import com.handbook.java.designmode.singleton.hungry.Hungry;
import com.handbook.java.designmode.singleton.lazy.LazyOne;
import com.handbook.java.designmode.singleton.lazy.LazyThree;
import com.handbook.java.designmode.singleton.lazy.LazyTwo;
import com.handbook.java.designmode.singleton.register.Register;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName App
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 16:20
 * @Version 1.0
 **/
public class AppTest {
    public static void main(String[] args) {
        // isSafe();
         // isSafe2();
        isSafe3();

       //  isEfficient();




    }

    public static void isSafe() {
        int count=500;
        CountDownLatch latch=new CountDownLatch(count);
        CountDownLatch latch2=new CountDownLatch(count);
        Set<Object> set_hungry = Collections.synchronizedSet(new HashSet<Object>());
        Set<Object> set_lazyOne = Collections.synchronizedSet(new HashSet<Object>());
        Set<Object> set_lazyTwo = Collections.synchronizedSet(new HashSet<Object>());
        Set<Object> set_lazyThree = Collections.synchronizedSet(new HashSet<Object>());
        Set<Object> set_register = Collections.synchronizedSet(new HashSet<Object>());
        Set<Object> set_enum = Collections.synchronizedSet(new HashSet<Object>());

        for(int i=0;i<count;i++){
            new Thread(() -> {
                try {
                    latch.await();
                    Hungry hungry= Hungry.getInstance();
                    set_hungry.add(hungry);
                    LazyOne lazyOne=LazyOne.getInstance();
                    set_lazyOne.add(lazyOne);
                    LazyTwo lazyTwo=LazyTwo.getInstance();
                    set_lazyTwo.add(lazyTwo);
                    LazyThree lazyThree=LazyThree.getInstance();
                    set_lazyThree.add(lazyThree);
                    Register register= (Register) Register.getInstance("com.handbook.java.designmode.singleton.register.Register");
                    set_register.add(register);
                    Object object=Enumerate.INSTANCE.getInstance();
                    set_enum.add(object);

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    latch2.countDown();
                }

            }).start();
            latch.countDown();
        }
        try {
            latch2.await();
            System.out.println("Hungry Size : "+set_hungry.size());
            System.out.println("LazyOne Size : "+set_lazyOne.size());
            System.out.println("LazyTwo Size : "+set_lazyTwo.size());
            System.out.println("LazyThree Size : "+set_lazyThree.size());
            System.out.println("Register Size : "+set_register.size());
            System.out.println("Enum Size : "+set_enum.size());


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void isEfficient() {
        int count=1000000;
        long start=System.currentTimeMillis();
        for (int i=0;i<count;i++){
            // Hungry hungry= Hungry.getInstance();

             LazyOne lazyOne=LazyOne.getInstance();

               //有性能问题
            //  LazyTwo lazyTwo=LazyTwo.getInstance();

            // LazyThree lazyThree=LazyThree.getInstance();
            //有性能问题
            // Register register= (Register) Register.getInstance("com.handbook.java.designmode.singleton.register.Register");
            //  Object object=Enumerate.INSTANCE.getInstance();
        }
        long end=System.currentTimeMillis();
        System.out.println("耗时 ： "+(end-start));
    }

    public static void isSafe2()  {
         LazyOne lazyOne=LazyOne.getInstance();
         System.out.println(lazyOne);
        FileOutputStream fos=null;

        try {
            fos=new FileOutputStream("lazyOne.obj");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(lazyOne);
            oos.flush();
            oos.close();

            FileInputStream fis=new FileInputStream("lazyOne.obj");
            ObjectInputStream ois=new ObjectInputStream(fis);
            LazyOne lazyOne2= (LazyOne) ois.readObject();
            ois.close();
            System.out.println(lazyOne2);
            System.out.println(lazyOne==lazyOne2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void isSafe3() {
       LazyOne lazyOne1=LazyOne.getInstance();
        try {
      LazyOne lazyOne2=LazyOne.class.newInstance();
      System.out.println(lazyOne1==lazyOne2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
