package com.me.leetcode;


/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-13 13:58
 **/
public class Practice7 {
    public static void main(String[] args) {
        int x=-12345;
        reverse(x);
    }

    /**
     *    2147483647     7
     *   -2147483648     8
     * @param x
     */
    private static int reverse(int x) {
        int y=0;
        while (x!=0){
            int value=x%10;
             x/=10;
             if(y>Integer.MAX_VALUE/10||(y==Integer.MAX_VALUE/10&&value>7)){return 0;}
             if(y<Integer.MIN_VALUE/10||(y==Integer.MIN_VALUE/10&&value>8)){return 0;}
             y=y*10+value;
        }
     return y;
    }
}
