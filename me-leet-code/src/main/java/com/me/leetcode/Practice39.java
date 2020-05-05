package com.me.leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 *   给定一个【无重复元素】的数组 candidates 和一个目标数 target ，
 *   找出 candidates 中所有可以使数字【和】为 target 的组合。
 *   candidates 中的数字可以【无限制重复】被选取。
 *
 *    所有数字（包括 target）都是【正整数】。
 *    解集不能包含重复的组合
 * @ClassName Practive
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/25 11:58
 * @Version 1.0
 **/
public class Practice39 {
    public static void main(String[] args) {
        int[] candidates=new int[]{2,3,6,7};
        int target=7;
        List<List<Integer>> list=combinationSum(candidates,target);
    }
    public static  List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates==null||candidates.length==0){return null;}
        Arrays.sort(candidates);
        if(candidates[0]>target){return null;}
        List<List<Integer>> list=new ArrayList<>();

        Set<Integer> set=new HashSet<>();
        for (int i = 0; i <candidates.length ; i++) {
          
            if(candidates[i]>target){break;}
            if(candidates[i]==target){
                List<Integer> l=  new ArrayList<Integer>();
              l.add(candidates[i]);
              list.add(l);
              break;
            }

           //自身
         if(target%candidates[i]==0){
             List<Integer> l=  new ArrayList<Integer>();
             for (int j = 0; j < (target/candidates[i]); j++) {
                 l.add(candidates[i]);
             }
             list.add(l);
         }
         //为防止重复，只和前面的比较
          if(i==0){continue;}
          if(candidates[i]+candidates[0]>target){
            //当前值 与最小值相加大于目标值不会再存在组合
              continue;
          }
         //candidates[0-i]从找组合


        }
        return list;

    }
}
