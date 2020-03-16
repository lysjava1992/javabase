package com.me.sort;

/**
 * @description: 插入排序
 * 外层循环 从1开始，即1之前是默认有序的
 * 内层循环 将左边未排序的向已排序的插入
 * 每次都是将arr[i]向有序集合中插入
 * @author: Mr.Luan
 * @create: 2020-03-09 14:26
 **/
public class Sort3 {
    public static void main(String[] args) {
        int length = 1000;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
//        System.out.println(Arrays.toString(arr));
//        for (int i = 1; i < length; i++) {
//            for (int j = i; j > 0; j--) {
//                 // arr[j] 是一个未排序的，而j之前的是有序
//                if (arr[j] < arr[j - 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j - 1];
//                    arr[j - 1] = temp;
//                } else {
//                    break;
//                }
//            }
//        }
//
//        System.out.println(Arrays.toString(arr));

        long start=System.currentTimeMillis();
        for (int i = 1; i < arr.length; i++) {
            int temp=arr[i];
            int index=i;
               for (int j=i-1;j>=0;j--){
                    if(arr[j]>temp){
                        arr[j+1]=arr[j];
                        index=j;
                    }
               }
            arr[index]=temp;
        }
        long end=System.currentTimeMillis();
        System.out.println("插入排序【"+length+"】耗时： "+(end-start));
       // System.out.println(Arrays.toString(arr));
    }
}
