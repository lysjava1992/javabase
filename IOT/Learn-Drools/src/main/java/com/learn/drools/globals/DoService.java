package com.learn.drools.globals;

public class DoService {
    private static int count=0;
    public void doSomeThing(){
        count++;
    }
    public int getCount(){
        return count;
    }
}
