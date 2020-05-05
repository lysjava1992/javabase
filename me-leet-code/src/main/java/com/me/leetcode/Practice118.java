package com.me.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 杨辉三角
 *
 * @ClassName Practice118
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/5/5 16:48
 * @Version 1.0
 **/
public class Practice118 {
    public static void main(String[] args) {
        List<List<Integer>> listList=   generate(4);
        if(listList!=null&&listList.size()>0){
            for (List<Integer> list:listList) {
                System.out.println(Arrays.toString(list.toArray()));
            }
        }
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> listList =new ArrayList();
        for (int i = 0; i <numRows; i++) {
            List<Integer>list=new ArrayList<>(i+1);
            // 首尾为1
            list.add(0,1);
            if(i>1){
                List<Integer> prev=listList.get(i-1);
                for (int j = 1; j < i; j++) {
                    int temp=prev.get(j)+prev.get(j-1);
                    list.add(j,temp);
                }

            }
            if(i>0){
                list.add(i,1);
            }
            listList.add(list);
        }


        return listList;
    }
}
