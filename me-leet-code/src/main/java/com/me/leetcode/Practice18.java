package com.me.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 * @author: Mr.Luan
 * @create: 2020-04-14 14:52
 **/
public class Practice18 {
    public static void main(String[] args) {
        List<List<Integer>> listList=  fourSum2(new int[]{-3,-2,-1,0,0,1,2,3}, 0);
        listList.stream().forEach((list)->{
             System.out.println(Arrays.toString(list.toArray()));
        });
    }

    /**
     *   排序
     *   固定两个指针
     *   第一层循环 固定  第一个
     *   第二层循环 固定 第二个
     *    三四指针移动
     *
     * @param nums
     * @param target
     * @return
     */

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums.length<4){return result;}
         Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        if(nums[0]+nums[1]+nums[2]+nums[3]>target){ return result;}
        int length=nums.length;
        if(nums[length-1]+nums[length-2]+nums[length-3]+nums[length-4]<target){return result;}

        for (int i=0;i<length-3;i++){
             //第一个固定值 与上次相同跳过  i从0 开始
            if(i>0&&nums[i]==nums[i-1]){ continue; }
            // 先检查 最小的值
            if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target){ break;}
            //再检查最大值
            if(nums[i]+nums[length-1]+nums[length-2]+nums[length-3]<target){continue;}

            for (int j=i+1;j<length-2;j++){


                //  与上次相同跳过
                if(j>(i+1)&&nums[j]==nums[j-1]){ continue; }

                //再次检查  有两个固定值 i j
                if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target){ continue;}
                //最大值  最小值 尽量缩小
                if(nums[i]+nums[j]+nums[length-1]+nums[length-2]<target){continue;}


                 //两个固定值 i j  定义两个游标值 n=j+1 最小值  m=length-1 最大值
                int n=j+1;
                int m=length-1;

                while (n<m){
                    int temp=nums[i]+nums[j]+nums[n]+nums[m];

                    if(temp==target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[n],nums[m]));
                        n++;
                        while (n<m&&nums[n]==nums[n-1]){
                            n++;
                        }
                    }else if(temp>target){ m--; }
                     else {n++;}
                }
            }
        }
        return result;

    }

    public static List<List<Integer>> fourSum2(int[] nums,int target){
        /*定义一个返回值*/
        List<List<Integer>> result=new ArrayList<>();
        /*当数组为null或元素小于4个时，直接返回*/
        if(nums==null||nums.length<4){
            return result;
        }
        /*对数组进行从小到大排序*/
        Arrays.sort(nums);
        /*数组长度*/
        int length=nums.length;
        /*定义4个指针k，i，j，h  k从0开始遍历，i从k+1开始遍历，留下j和h，j指向i+1，h指向数组最大值*/
        for(int k=0;k<length-3;k++){
            /*当k的值与前面的值相等时忽略*/
            if(k>0&&nums[k]==nums[k-1]){
                continue;
            }
            /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
            int min1=nums[k]+nums[k+1]+nums[k+2]+nums[k+3];
            if(min1>target){
                break;
            }
            /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
            int max1=nums[k]+nums[length-1]+nums[length-2]+nums[length-3];
            if(max1<target){
                continue;
            }
            /*第二层循环i，初始值指向k+1*/
            for(int i=k+1;i<length-2;i++){
                /*当i的值与前面的值相等时忽略*/
                if(i>k+1&&nums[i]==nums[i-1]){
                    continue;
                }
                /*定义指针j指向i+1*/
                int j=i+1;
                /*定义指针h指向数组末尾*/
                int h=length-1;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏，忽略*/
                int min=nums[k]+nums[i]+nums[j]+nums[j+1];
                if(min>target){
                    continue;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                int max=nums[k]+nums[i]+nums[h]+nums[h-1];
                if(max<target){
                    continue;
                }
                /*开始j指针和h指针的表演，计算当前和，如果等于目标值，j++并去重，h--并去重，当当前和大于目标值时h--，当当前和小于目标值时j++*/
                while (j<h){
                    int curr=nums[k]+nums[i]+nums[j]+nums[h];
                    if(curr==target){
                        result.add(Arrays.asList(nums[k],nums[i],nums[j],nums[h]));
                        j++;
                        while(j<h&&nums[j]==nums[j-1]){
                            j++;
                        }
                        h--;
                        while(j<h&&i<h&&nums[h]==nums[h+1]){
                            h--;
                        }
                    }else if(curr>target){
                        h--;
                    }else {
                        j++;
                    }
                }
            }
        }
        return result;
    }



}
