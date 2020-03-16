package com.me.leetcode;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Solution11
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/24 8:43
 * @Version 1.0
 **/
public class Practice11 {
    public static void main(String[] args) {
        int[] arr={1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(arr));
    }
    public static int maxArea(int[] height) {
        int max=0;
   for (int i=0;i<height.length-1;i++){
       for (int j=i+1;j<height.length;j++){
           int temp=height[i]<height[j]?height[i]:height[j];
           max=max>temp*(j-i)?max:temp*(j-i);
       }
   }
         return max;
    }
}
