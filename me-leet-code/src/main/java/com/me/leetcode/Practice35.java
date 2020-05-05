package com.me.leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 *       如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *   假设数组中无重复元素。
 *
 * @ClassName Practice35
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/25 11:32
 * @Version 1.0
 **/
public class Practice35 {
    public static void main(String[] args) {
        int[]  nums=new int[]{1,3};
        System.out.println(searchInsert(nums,2));
    }
    public static int searchInsert(int[] nums, int target) {
        int length=nums.length;
      if(nums==null||nums.length<1){ return 0; }
      if(nums[0]>target){ return 0; }
      if(nums[length-1]<target){ return length; }

      //此时 目标值一定 ：
        // 1.在数据组中二分法
        // 2.在数组中某个非边缘位置插入
       int start=0;
       int end=length-1;
        int mid=start+(end-start)/2;
       while (start<end){
           if(nums[mid]==target){
               break;
           }else if(nums[mid]>target){
               if(nums[mid-1]<target){
                   break;
               }
               end=mid--;
           }else {
               if(nums[mid+1]>target){
                   mid=mid+1;
                   break;
               }
               start=mid++;

           }

       }
       return mid;
    }
}
