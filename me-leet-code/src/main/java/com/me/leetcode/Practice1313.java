package com.me.leetcode;

import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 解压缩编码列表
 * 输入：nums = [1,2,3,4]
 *   输出：[2,4,4,4]
 *   解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
 *   第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
 *    最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。
 * @author: Mr.Luan
 * @create: 2020-04-26 10:03
 **/
public class Practice1313 {
    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3,4};
        decompressRLElist(nums);
    }
    public static int[] decompressRLElist(int[] nums) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <nums.length ; i=i+2) {
            for (int j = 0; j <i ; j++) {
                list.add(nums[i+1]);
            }
        }
        int[] arr=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i]=list.get(i);
        }
        return arr;
    }
}
