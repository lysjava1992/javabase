package com.me.leetcode;

import java.util.*;

/**
 * @description: 三数之和
 *      [-1, 0, 1, 2, -1, -4]
 *       a+b+c=0
 *       [-1, 0, 1],
 *       [-1, -1, 2]
 *       不重复
 * @author: Mr.Luan
 * @create: 2020-03-25 11:20
 **/
public class Practice15 {
    public static void main(String[] args) {
        int[] nums={-2,0,0,2,2};

         threeSum(nums);
    }

    /**
     * 三数和
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i <nums.length-2 ; i++) {
            //最小值大于0 相加不会有=0的情况
         if(nums[i]>0){ break; }
             //去重
         if(i>0&&nums[i]==nums[i-1]){continue;}
            int small =i+1;
            int big=nums.length-1;
            while (small<big){
                if(big<nums.length-2&&nums[big]==nums[big+1]){
                   big--;
                   continue;
                }
                if(small>i+1&&nums[small]==nums[small-1]){
                    small++;
                    continue;
                }

                if((nums[small]+nums[big]+nums[i])>0){
                  big--;
                }else if((nums[small]+nums[big]+nums[i])<0){
                   small++;
                }else {
                    List<Integer> list=new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[small]);
                    list.add(nums[big]);
                    lists.add(list);
                    big--;
                    small++;
                }

            }
        }
        return lists;
    }
}
