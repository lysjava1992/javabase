package com.learn.lombok;

import lombok.val;
import lombok.var;
import java.util.ArrayList;
import java.util.HashMap;


public class ValExample {
    public String example(){
        var example=new ArrayList<String>();
        example.add("Hello,World");
        var foo=example.get(0);
        return foo.toLowerCase();
    }
    public void example2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println(new ValExample().example());
        new ValExample().example2();
    }
}
