package com.me.sort;

import java.util.Arrays;

/**
 * @description: 选择排序
 *                外层循环 控制次数
 *                内层循环 选择极值
 *                复杂度 O（n²）
 *                稳定定 不稳定
 * @author: Mr.Luan
 * @create: 2020-03-09 14:05
 **/
public class Sort2 {
    public static void main(String[] args) {
        int length = 11;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 100 + 1);
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < length - 1; i++) {
            int min = arr[i];
            int min_index = i;
            for (int j = i; j < length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    min_index = j;
                }
            }
            if (min_index > i) {
                int temp = arr[i];
                arr[i] = min;
                arr[min_index] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < length - 1; i++) {
            int max = i;
            for (int j = i; j < length; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            if (max > i) {
                int temp = arr[i];
                arr[i] = arr[max];
                arr[max] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
