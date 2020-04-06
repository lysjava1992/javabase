package com.me.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

 字符          数值
 I             1
 V             5
 X             10
 L             50
 C             100
 D             500
 M             1000
 *
 * @ClassName Practice13
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/6 9:54
 * @Version 1.0
 **/
public class Practice13 {
    public static void main(String[] args) {
       System.out.println(romanToInt1("MCMXCIV"));
        System.out.println(romanToInt2("MCMXCIV"));
      
    }

    private static int romanToInt2(String s) {

        int result=0;
        int pre=getValue(s.charAt(0));
        for (int i=1;i<s.length();i++){
            if(getValue(s.charAt(i))>pre){
                result -=pre;
            }else {
                result+=pre;
            }
            pre=getValue(s.charAt(i));
        }

        return result+=pre;
    }
    private static  int getValue(char c){
        switch (c){
            case 'I' :return 1;
            case 'V' :return 5;
            case 'X' :return 10;
            case 'L' :return 50;
            case 'C' :return 100;
            case 'D' :return 500;
            case 'M' :return 1000;
            default:return 0;
        }
    }

    public  static  int romanToInt1(String s) {
        Map<Character,Integer> map=new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        if(s.length()==1){
            return map.get(s.charAt(0));
        }
        int result=0;
        for (int i = 0; i <s.length() ; i++) {
            if(i==s.length()-1){
                result+=map.get(s.charAt(i));
                break;
            }
            if(map.get(s.charAt(i))>=map.get(s.charAt(i+1))){
                result+=map.get(s.charAt(i));
            }else {
                result-=map.get(s.charAt(i));
            }
        }
          return result;
    }
}
