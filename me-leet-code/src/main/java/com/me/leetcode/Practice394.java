package com.me.leetcode;
import java.util.Stack;
/**
 *
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *    输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *    原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 *
 * 示例:
 *   s = "3[a]2[bc]", 返回 "aaabcbc".
 *   s = "3[a2[c]]", 返回 "accaccacc".
 *   s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-28 08:40
 **/
public class Practice394 {
    public static void main(String[] args) {
         System.out.println(decodeString("4[a3[b]]"));

    }
    public static String decodeString(String s){
              Stack<Integer> countStack=new Stack<>();
              Stack<String> strStack=new Stack<>();
               int count=0;
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i <s.length() ; i++) {
            char c=s.charAt(i);
            if(c>='0'&&c<='9'){
                count=count*10+(c-'0');
            }else if(c=='['){
                //此时意味着计数已经结束, 入栈
                countStack.push(count);
                count=0;
                //
                strStack.push(sb.toString());
                sb=new StringBuilder();
            }else if(c==']'){
                //意味着 字符串已经结束
                //取出重复次数次数
                int redo=countStack.pop();
                //preSb是之前的字符串
                StringBuilder preSB = new StringBuilder().append(strStack.pop());
                if(redo==3){System.out.println(preSB.toString());}
                //sb是当次的字符串

                for (int j = 0; j < redo; j++) {
                    preSB.append(sb);
                }
                sb = preSB;
            }else {
                //字符串
                sb.append(c);
            }

        }
    return sb.toString();
    }
}
