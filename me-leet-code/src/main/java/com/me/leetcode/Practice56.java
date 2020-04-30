package com.me.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *   示例 1
 *    输入: [[1,3],[2,6],[8,10],[15,18]]
 *    输出: [[1,6],[8,10],[15,18]]
 *    解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6]
 *  示例 2
 *   输入: [[1,4],[4,5]]
 *   输出: [[1,5]]
 *   解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-27 13:56
 **/
public class Practice56 {
    public static void main(String[] args) {
        int[][] ints=new int[][]{
//                new int[]{2,3},
//                new int[]{4,5},
//                new int[]{6,7},
//                new int[]{8,9},
//               new int[]{1,10}
//                new int[]{2,3},
//                new int[]{2,2},
//                new int[]{3,3},
                new int[]{1,3},
                new int[]{5,7},
           //     new int[]{2,2},
                new int[]{4,6},
        };
        Arrays.sort(ints,(v1, v2) -> v1[0] - v2[0]);
        for (int[] arr:ints) {
            System.out.println(Arrays.toString(arr));
        }
        ints= merge(ints);
        System.out.println("================================");
        for (int[] arr:ints) {
           System.out.println(Arrays.toString(arr));
        }
    }
    public static int[][] merge(int[][] intervals) {
       if(intervals.length<=1){return intervals;}
        Arrays.sort(intervals,(v1, v2) -> v1[0] - v2[0]);
        List<int[]> listList=new ArrayList<>();
        listList.add(intervals[0]);
        boolean status=false;
        for (int i = 1; i <intervals.length ; i++) {
            status=false;
            for (int[] arr:listList) {
                if(arr[1]<(intervals[i][0]) ||
                   arr[0]>(intervals[i][1])     ){
                    status=true;
                }else {
                    status=false;
                    arr[0]=arr[0]<=intervals[i][0]?arr[0]:intervals[i][0];
                    arr[1]=arr[1]>=intervals[i][1]?arr[1]:intervals[i][1];
                }
            }
            if(status){listList.add(intervals[i]);}
        }
       int [][] ints=new int[listList.size()][2];
        for (int i = 0; i <listList.size() ; i++) {
            ints[i]=listList.get(i);
        }
         return ints;
    }
}
