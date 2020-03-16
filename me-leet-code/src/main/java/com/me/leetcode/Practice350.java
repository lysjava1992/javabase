package com.me.leetcode;

import java.util.*;

/**
 * @description:
 *  数组交集
 * @author: Mr.Luan
 * @create: 2020-03-12 16:11
 **/
public class Practice350 {
    public static void main(String[] args) {
        int[]nums1 = {1,2,2,1};
        int[]nums2 = {2,3};
        intersect1(nums1,nums2);
        intersect2(nums1,nums2);

    }

    /**
     *  hash
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int key:nums1){
             map.merge(key,1,Integer::sum);
        }
        List<Integer> result=new ArrayList<>();
        for (int key:nums2){
            if(map.containsKey(key)&&map.get(key)>0){
                result.add(key);
                map.merge(key,-1,Integer::sum);
            }
        }
        int[] arr=new int[result.size()];
        int i=0;
        for (Integer integer:result){
            arr[i++]=integer;
        }
        System.out.println(Arrays.toString(arr));
        return nums1;
    }

    /**
     * 排序遍历
     * @param nums1
     * @param nums2
     */
    private static int[] intersect1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result=new ArrayList<>();

        int index_1=0,index_2=0;
        while (index_1<nums1.length&&index_2<nums2.length){
            if(nums1[index_1]==nums2[index_2]){
                result.add(nums1[index_1]);
                index_1++;
                index_2++;
            }else if(nums1[index_1]>nums2[index_2]){
                index_2++;
            }else {
                index_1++;
            }
        }
         int[] arr=new int[result.size()];
         int i=0;
         for (Integer integer:result){
             arr[i++]=integer;
         }
         System.out.println(Arrays.toString(arr));
        return arr;
    }
}
