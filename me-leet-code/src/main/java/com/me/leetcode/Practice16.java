package com.me.leetcode;

import java.util.Arrays;
import java.util.Map;

/**
 *给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *    例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *    与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * @ClassName Practice16
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/11 10:30
 * @Version 1.0
 **/
public class Practice16 {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4},1));

    }
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int threeSumClosest=nums[0]+nums[1]+nums[nums.length-1];
        int diff=Math.abs(target-threeSumClosest);
        for (int i = 0; i <nums.length-2 ; i++) {
              int min=i+1;
              int max=nums.length-1;
              while (min<max){
               int sun=nums[i]+nums[min]+nums[max];
               if(sun<target){
                   min++;
               }else {
                   max--;
               }
               if(Math.abs(sun-target)<diff){
                   diff=Math.abs(sun-target);
                   threeSumClosest=sun;
               }
              }
        }
    return threeSumClosest;
    }
}
