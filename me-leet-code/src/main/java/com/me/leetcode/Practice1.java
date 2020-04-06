package com.me.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *  两数和
 *  给定一个整数数组 nums 和一个目标值 target，
 *  该数组中找出和为目标值的那 两个整数，
 *  并返回他们的数组下标。
 *
 * @ClassName Practice1
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/5 10:00
 * @Version 1.0
 **/
public class Practice1 {
    public static void main(String[] args) {
        int [] nums = {3,3};
        int target = 6;
        System.out.println(Arrays.toString(twoSum1(nums,target)));
        System.out.println(Arrays.toString(twoSum2(nums,target)));
    }

    public static int[] twoSum1(int[] nums, int target) {
        int[] result=new int[2];
        for (int i = 0; i <nums.length-1 ; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    break;
                }
            }
        }
        return result;
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            if(map.containsKey(nums[i])){

                return new int[]{map.get(nums[i]), i};
            }else {
                map.put(target-nums[i],i);
            }
        }
        return null;
    }
}
