package com.me.leetcode;

import java.util.Arrays;

/**
 * @description: 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置
 * 算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * @author: Mr.Luan
 * @create: 2020-04-20 08:48
 **/
public class Practice34 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1};
        System.out.println(Arrays.toString(searchRange(nums, 1)));
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length < 1) {
            return result;
        }

        int start = 0;
        int end = nums.length - 1;
        boolean status = false;
        //二分法 查找目标值
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                status = true;
                start = end = mid;
                break;
            }
        }
        //不存在
        if (!status) {
            return result;
        }

        //存在 亿目标值为中心 扩散
        while (start >= 0) {
            if (nums[start] == target ) {
                if(start==0){break;}
                start--;
            } else {
                    start++;

                break;
            }
        }
        while (end < nums.length) {
            if (nums[end] == target ) {
                if(end==nums.length-1){break;}
                end++;
            } else {
                end--;
                break;
            }
        }
        result[0] = start;
        result[1] = end;
        return result;
    }
}
