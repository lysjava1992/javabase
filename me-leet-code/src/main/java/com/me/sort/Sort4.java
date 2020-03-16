package com.me.sort;

/**
 * @description: 希尔排序
 *        外层 while 循环控制步数  step=length/2   step/=2
 *        内层 双for循环 插入排序
 * @author: Mr.Luan
 * @create: 2020-03-09 15:45
 **/
public class Sort4 {
    public static void main(String[] args) {
        int length = 1000;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        int step = length / 2;
        while (step > 0) {
            for (int i = step; i < length; i++) {
                int temp = arr[i]; //要插入的值
                int index = i;      //初始下表
                for (int j = i - step; j >= 0; j = j - step) {
                    if (arr[j] > temp) { //后移
                        arr[j + step] = arr[j];
                        index = j;
                    } else {
                        break;
                    }
                    arr[index] = temp;
                }
            }
            step /= 2;
        }
        long end = System.currentTimeMillis();
        System.out.println("希尔排序【" + length + "】耗时： " + (end - start));
        //  System.out.println(Arrays.toString(arr));
    }
}
