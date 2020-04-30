package com.me.leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * 在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
 *    如果小镇的法官真的存在，那么小镇的法官不相信任何人。
 *    每个人（除了小镇法官外）都信任小镇的法官。
 *    只有一个人同时满足属性 1 和属性 2 。
 *    给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
 *    如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-30 09:11
 **/
public class Practice997 {
    public static void main(String[] args) {
       int[] [] trust= new int[][]{
              // new int[]{1,2},
          //     new int[]{2,3},
               new int[]{1,3},
               new int[]{1,4},
               new int[]{2,3},
               new int[]{2,4},
               new int[]{4,3},
       };
       System.out.println( findJudge2(4, trust));
    }
    public static int findJudge(int N, int[][] trust) {
        if(N==1&&trust.length==0){return N;}
        //投票的人
        Set<Integer> set=new HashSet<>();
        //每个人得票数
        Map<Integer,Integer> map=new HashMap<>();
        for (int[] arr: trust) {
            set.add(arr[0]);
            int count=map.getOrDefault(arr[1],0);
             map.put(arr[1],++count);
        }

        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
             System.out.println(entry.getValue());
            //   得票数==N-1 而且       本人没有参加投票
            if(entry.getValue()==(N-1)&&!set.contains(entry.getKey())){

                 return entry.getKey();
            }


        }
        return -1;
    }

    /**
     * 两个数组  长度 N+1
     *  下标 代表第N个人
     *
     *  第一个数组 投票次数
     *  第二个数组 获得票次数
     *
     *
     *
     * @param N
     * @param trust
     * @return
     */
    public static int findJudge2(int N, int[][] trust){
        int[] vote=new int[N+1];
        int[] votesWon=new int[N+1];

        for (int [] arr:trust) {
            // arr[0] 表示其 投票了
            vote[arr[0]]=vote[arr[0]]+1;
            // arr[1] 表示其获得了一票
            votesWon[arr[1]]=votesWon[arr[1]]+1;
        }
        for (int i = 1; i <(N+1) ; i++) {
            if(votesWon[i]==(N-1)&&vote[i]==0){
                return i;
            }
        }
        return -1;
    }
}