package com.handbook.java.reflection;

import org.junit.Test;

public class DemoTest5 {
    @Test
    public void test(){
           JdkAgent jdkAgent=new JdkAgent();
           Animal dog= (Animal) jdkAgent.getInstance(new Dog());
           dog.run();
    }

    @Test
    public void test1() throws ClassNotFoundException {
        ClassLoader classLoader=DemoTest5.class.getClassLoader();
        Class aclass=classLoader.loadClass("com.handbook.java.reflection.Dog");
        System.out.println("aclass: "+aclass.getName());
    }



    public static void main(String[] args) {
        for (int i = 0; i <100 ; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
