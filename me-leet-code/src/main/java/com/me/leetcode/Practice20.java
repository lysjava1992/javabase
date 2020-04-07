package com.me.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description:
 *给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *          有效字符串需满足：
 *          左括号必须用相同类型的右括号闭合。
 *         左括号必须以正确的顺序闭合。
 * @author: Mr.Luan
 * @create: 2020-04-07 08:39
 **/
public class Practice20 {
    public static void main(String[] args) {
        System.out.println(isValid("}"));
    }
    public static boolean isValid(String s) {
        Map<Character,Character> map=new HashMap();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        Stack<Character> stack=new Stack<>();
        for (Character c:s.toCharArray()) {
            if(map.containsKey(c)){
                stack.push(c);
            }else {
                if(stack.isEmpty()){return false;}
               Character c1=stack.pop();
                if(!map.containsKey(c1)||!map.get(c1).equals(c)){
                    return false;
                }
            }
        }
        if(!stack.isEmpty()){return false;}
        return true;
    }

}
