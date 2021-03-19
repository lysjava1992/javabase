package com.learn.lombok;

import lombok.NonNull;

public class NonNullExample {
    private String name;

    public NonNullExample(@NonNull  String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        NonNullExample example=new NonNullExample(null);
       // System.out.println(example.getName().length());
    }
}
