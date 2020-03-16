package com.me.sort;

import java.util.Arrays;

/**
 * @description:  快速排序
 *        分而治之
 *       O(n log n)
 * @author: Mr.Luan
 * @create: 2020-03-11 14:35
 **/
public class Sort6 {
    public static void main(String[] args) {
        int length=10;
        int[] arr=new int[length];
        for (int i=0;i<length;i++){
            arr[i]= (int) (Math.random()*100);
        }
        System.out.println(Arrays.toString(arr));
        quicksort(arr,0,length-1);
        System.out.println(Arrays.toString(arr));
    }
    /**
     *   与归并不同 分割点并不单纯
     *               分割点是已排好序的
     *               即已经确定的分割点不需要再参与排序
     * @param arr
     * @param start
     * @param end
     */
    private static void quicksort(int[] arr, int start, int end) {
        if(start>=end){return;}
         int left=start;
         int right=end;
         int point=arr[start];
        //找分割点  将arr[start]-arr[end] 调整   left全部是<point,right全部是>end
        while (left<right){
            //右指针 不小于poit一直走
            while (right>left&&arr[right]>=point){right--;}
            if(arr[right]<point){
               //交换前后指针位置的数据
                int temp=arr[left];
                arr[left]=arr[right];
                arr[right]=temp;
            }
             //左指针再走
            while (left<right&&arr[left]<=point){ left++; }
            if(arr[left]>point){
                int temp=arr[left];
                arr[left]=arr[right];
                arr[right]=temp;
            }
        }
          if(start<left){quicksort(arr, start, left-1); }
          if(right<end){quicksort(arr, right+1, end); }
    }

}
