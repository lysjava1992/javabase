package com.me.sort;

import java.util.Arrays;

/**
 * @description:   归并排序
 *      将数组递归拆分 最小单位2 1
 *      分而治之
 *      O(n log n)
 * @author: Mr.Luan
 * @create: 2020-03-11 13:37
 **/
public class Sort5 {
    public static void main(String[] args) {
        int length=10;
        int[] arr=new int[length];
        for (int i=0;i<length;i++){
            arr[i]= (int) (Math.random()*100);
        }
        System.out.println(Arrays.toString(arr));
        part(arr,0,length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分
     * @param arr
     * @param start
     * @param end
     */
    private static void part(int[] arr, int start, int end) {
        if(start>=end){return;}
        int middle=(start+end)/2;
        part(arr,start,middle);
        part(arr,middle+1,end);
        megre(arr,start,middle,end);
    }

    /**
     * 合 排序
     *  逻辑上两个有序数组
     *    arr[start]-——arr[middle]
     *    arr[middle+1]-——arr[end]
     *
     * @param arr
     * @param start
     * @param middle
     * @param end
     */
    private static void megre(int[] arr, int start, int middle, int end) {
        int start_1=start;
        int start_2=middle+1;
        //临时数组存包合并有序值
        int[] arr_temp=new int[(end-start+1)];
        int arr_index=0;

        //有序数组合并
         while (start_1<=middle&&start_2<=end){
             if(arr[start_1]<arr[start_2]){
                 arr_temp[arr_index++]=arr[start_1++];
             }else {
                 arr_temp[arr_index++]=arr[start_2++];
             }
         }
         //排空两个数组
        while (start_1<=middle){
            arr_temp[arr_index++]=arr[start_1++];
        }
        while (start_2<=end){
            arr_temp[arr_index++]=arr[start_2++];
        }

        //将临时有序组放回原来数组
        for (int i=0;i<arr_temp.length;i++){
             arr[start++]=arr_temp[i];
        }
    }
}
