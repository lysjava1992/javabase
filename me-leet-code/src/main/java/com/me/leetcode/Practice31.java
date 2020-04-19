package com.me.leetcode;

import java.util.Arrays;

/**
 * @description:
 *   实现获取下一个排列的函数，
 *  算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *  如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *  必须原地修改，只允许使用额外常数空间。
 *  以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 *         1,2,3 → 1,3,2
 *         3,2,1 → 1,2,3
 *         1,1,5 → 1,5,1

 * @author: Mr.Luan
 * @create: 2020-04-17 16:39
 **/
public class Practice31 {
    public static void main(String[] args) {
        int [] arr=new int[]{1,2,3,5,4};
        nextPermutation(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     *   1, 2, 3, (4), 5
     *   1, 2, (3), 5, 4
     *   1, 2, 4, (3), 5
     *   1, 2, (4), 5, 3
     *   1, 2, 5, (3), 4
     *   1, (2), 5, 4, 3
     *   1, 3, 2, (4), 5
     *   1, 3, (2), 5, 4
     *   1, 3, 4, (2), 5
     *   ......
     *  找到临界点  将临界点之后的数排序 保证是最小序列
     *   再找到 有序数列中中最小的比临界点大的数交换位置
     * @param nums
     */
    public static  void nextPermutation(int[] nums) {

        int len = nums.length;
        for (int i = len - 1; i >= 0; i--) {
            if (i == 0) {
                Arrays.sort(nums);
                return;
            } else {
                if (nums[i] > nums[i - 1]) {

                    Arrays.sort(nums, i, len);
                    for (int j = i; j <len; j++) {
                        if (nums[j] > nums[i - 1]) {
                            int temp = nums[j];
                            nums[j] = nums[i - 1];
                            nums[i - 1] = temp;
                            return;
                        }
                    }
                }
            }
        }


    }
}
