package com.handbook.java.clone;

public class CloneTest {
    public static void main(String[] args) throws Exception {
        Person p1=new Person("Tom",33,new Car("Ben",300));
        Person p2=MyUtil.clone(p1);
         System.out.println(p1==p2);
        p2.getCar().setBrand("BYD");

    }
}
