package com.handbook.java.datastructure.stack;

import java.util.Arrays;

public class ArrayStack {
    private String[] stack;
    private int count;  //元素个数
    private int size;   //栈的大小

    public ArrayStack(int size) {
        this.size = size;
        this.stack=new String[size];
        this.count=0;
    }
    public boolean instack(String str){
        if(count==size){
            stack = Arrays.copyOf(stack, 2 * size);
            size=2*size;
        }
        stack[count]=str;
        count++;
        return true;
    }
    public String outstack(){
         if(count==0)
            return null;
         count--;
        return stack[count];
    }
}
