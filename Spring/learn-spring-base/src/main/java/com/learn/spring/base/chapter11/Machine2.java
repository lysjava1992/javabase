package com.learn.spring.base.chapter11;

public  abstract class Machine2 {
    public void  doWork(){
        Parts parts=installParts();
        System.out.println("------work----");
        System.out.println(parts.getId()+"   is used");
    }
    protected abstract Parts installParts();
}
