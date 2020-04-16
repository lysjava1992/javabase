package com.me.leetcode;

import java.util.Arrays;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素
 * 并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素
 * @ClassName Practice27
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/16 19:48
 * @Version 1.0
 **/
public class Practice27 {
    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{},0));
    }
    public static int removeElement(int[] nums, int val) {
        if(nums.length==0){return 0;}
                int start=0;
                int end=nums.length-1;
        for (; start< end; start++) {
            if(nums[start]==val){
                int temp=nums[start];
                nums[start--]=nums[end];
                nums[end--]=temp;
            }
        }
        if(nums[start]==val){
            return start;
        }
        return start+1;
    }
}
