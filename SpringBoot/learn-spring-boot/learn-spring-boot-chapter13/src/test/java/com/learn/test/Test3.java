package com.learn.test;

import java.util.Random;

public class Test3 {
    public static void main(String[] args) {
        Random random=new Random();
        while (true){
            System.out.println(random.nextInt(3)+1);
        }
    }
}
