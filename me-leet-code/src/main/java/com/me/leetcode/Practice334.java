package com.me.leetcode;

import org.omg.PortableInterceptor.INACTIVE;

/**
 *递增的三元子序列
 *  给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
 *  数学表达式如下:
 *  如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 *   使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * @ClassName Practice334
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/4 10:23
 * @Version 1.0
 **/
public class Practice334 {
    public static void main(String[] args) {
        int[] nums={2,4,-2,-3};
     System.out.println(increasingTriplet(nums));
    }
    public static  boolean increasingTriplet(int[] nums) {
        int one = Integer.MAX_VALUE;
        int two = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num>two){
                return true;
            }else if (num>one){
                two = num;
            }else {
                one=num;
            }
        }
        return false;
    }
}
