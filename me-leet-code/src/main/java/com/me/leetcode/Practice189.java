package com.me.leetcode;

import java.util.Arrays;

/**
 * @description: 给定一个数组 将数组中的元素向右移动 K
 * @author: Mr.Luan
 * @create: 2020-03-11 17:17
 **/
public class Practice189 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int k = 2;
        move1(arr, k);
        // move2(arr,k);
        move3(arr, k);
    }
    private static void move3(int[] arr, int k) {
        int length = arr.length;
        k %= length;
        // 第一次交换完毕后，前 k 位数字位置正确，后 n-k 位数字中最后 k 位数字顺序错误，继续交换
        for (int start = 0; start < arr.length && k != 0; length -= k, start += k, k %= length) {
            for (int i = 0; i < k; i++) {
                int temp = arr[start + i];
                arr[start + i] = arr[arr.length - k + i];
                arr[arr.length - k + i] = temp;
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("move3:  " + Arrays.toString(arr));
    }
    /**
     * 外层循环  k 平移步数-》遍历次数
     * 内层循环  遍历平移1步
     * 循环k此
     *
     * @param arr
     * @param k
     */
    private static void move2(int[] arr, int k) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < k; i++) {  //平移次数
            int value = arr[arr.length - 1];
            for (int j = 0; j < arr.length; j++) {
                int temp = arr[j];
                arr[j] = value;
                value = temp;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("move2耗时：【" + (end - start) + "】");
        System.out.println("move2:  " + Arrays.toString(arr));
    }
    /**
     * 一次循环  额外空闲存放平移后的数组
     *
     * @param arr
     * @param k
     */
    private static void move1(int[] arr, int k) {
        long start = System.currentTimeMillis();
        int[] result = new int[arr.length];
        k=k%arr.length;
        for (int i = 0; i < arr.length; i++) {
            int index = i + k;
            if (index >= arr.length) {
                index = index - arr.length;
            }
            result[index] = arr[i];
        }
        long end = System.currentTimeMillis();
        System.out.println("move1耗时：【" + (end - start) + "】");
        System.out.println("move1:  " + Arrays.toString(result));
    }
}
