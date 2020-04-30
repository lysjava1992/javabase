package com.me.leetcode;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-26 09:50
 **/
public class Practice1295 {
    public static void main(String[] args) {
         int[] nums=new int[]{12,345,2,6,7896};
    }
    public static int findNumbers(int[] nums) {
        int result=0;
        for (int num:nums) {
            String strNum=String.valueOf(num);
            if(strNum.length()%2==0){
                result++;
            }
        }
        return result;
    }
}
