package com.me.sort;


import java.util.Arrays;

/**
 * @description:   冒泡排序
 *           外层循环 控制次数
 *           内存循环 比较交换
 *          复杂度 0（n²）
 *          稳定性 稳定
 * @author: Mr.Luan
 * @create: 2020-03-09 13:43
 **/
public class Sort1 {
    public static void main(String[] args) {
        int length = 13;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 100 + 1);
        }
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
