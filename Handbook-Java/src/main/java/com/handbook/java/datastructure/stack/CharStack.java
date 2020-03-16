package com.handbook.java.datastructure.stack;

import java.util.Arrays;

public class CharStack {
    private char[] stack;
    private int count;  //元素个数
    private int size;   //栈的大小

    public int getCount() {
        return count;
    }

    public CharStack(int size) {
        this.size = size;
        this.stack=new char[size];
        this.count=0;
    }
    public boolean instack(char str){
        if(count==size){
            stack = Arrays.copyOf(stack, 2 * size);
            size=2*size;
        }
        stack[count]=str;
        count++;
        return true;
    }
    public char outstack(){
         if(count==0)
            return 0;
         count--;
        return stack[count];
    }
}
