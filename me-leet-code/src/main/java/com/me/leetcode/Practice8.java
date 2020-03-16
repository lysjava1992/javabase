package com.me.leetcode;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-13 14:59
 **/
public class Practice8 {
    public static void main(String[] args) {
        String str = "-2147483649";
        System.out.println(myAtoi(str));
    }

    private static int myAtoi(String str) {
        str=str.trim();
        int result=0;
        int status=1;
        if(str.equals("")){return 0;}
        char[] arr=str.toCharArray();
        if(arr[0]=='-'){
            status=-1;
        }else if(arr[0]=='+'){
            status=1;
        }else if(arr[0]>='0'&&arr[0]<='9'){
            result=(arr[0]-'0');
        }else {
            return 0;
        }

        for (int i=1;i<arr.length;i++){
            if(arr[i]>='0'&&arr[i]<='9'){
                 int value=arr[i]-'0';
                if(result*status>Integer.MAX_VALUE/10||(result*status==Integer.MAX_VALUE/10&&value>7)){
                    return Integer.MAX_VALUE;}
                if(result*status<Integer.MIN_VALUE/10||(result*status==Integer.MIN_VALUE/10&&value>8)){
                    return Integer.MIN_VALUE;}
                  result=result*10+value;
            }else {
                break;
            }
        }
        return  result*status;
    }
}
