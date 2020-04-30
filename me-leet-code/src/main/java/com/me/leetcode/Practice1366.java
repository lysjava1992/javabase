package com.me.leetcode;

import java.util.*;

/**
 *  根据投票排队
 *
 *  输入：votes = ["ABC","ACB","ABC","ACB","ACB"]
 *  输出："ACB"
 *  解释：A 队获得五票「排位第一」，没有其他队获得「排位第一」，所以 A 队排名第一。
 *  B 队获得两票「排位第二」，三票「排位第三」。
 *  C 队获得三票「排位第二」，两票「排位第三」。
 *  由于 C 队「排位第二」的票数较多，所以 C 队排第二，B 队排第三。
 *
 *     参赛团队的排名次序依照其所获「排位第一」的票的多少决定。
 *     如果存在多个团队并列的情况，将继续考虑其「排位第二」的票的数量。以此类推，直到不再存在并列的情况。
 *
 *     如果在考虑完所有投票情况后仍然出现并列现象，则根据团队字母的字母顺序进行排名。
 *
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-29 09:27
 **/
public class Practice1366 {
    public static void main(String[] args) {
       String [] votes=new String[]{"WXYZ","XYZW"};
       System.out.println(rankTeams(votes));
    }

    /**
     *  1. 统计
     *      Map<Character,int[]>  ：
     *         Character代表被投者 A B 还是C
     *         int[] 计数 ，长度为votes[0].length 不管多少人投票 对于单个出现的位置是有限的
     *         int[] 下表代表在某个位置（0开始 代表1）
     *  2. 比较
     *
     * @param votes
     * @return
     */
    public  static  String rankTeams(String[] votes) {
          if(votes.length==1){return votes[0];}

          //候选人个数
          int candidate=votes[0].length();

          Map<Character,int[]> map=new HashMap<>();
          for (String vote:votes) {
              for (int i = 0; i < vote.length(); i++) {
                   int[] counts=map.getOrDefault(vote.charAt(i),new int[candidate]);
                   counts[i]=counts[i]+1;
                   map.put(vote.charAt(i),counts);
              }
         }
           for (Map.Entry<Character,int[]> entry:map.entrySet()){
               System.out.println(entry.getKey()+":   "+Arrays.toString(entry.getValue()));
           }
         // map转list
        List<Map.Entry<Character,int[]>> list = new ArrayList<>(map.entrySet());
         // 排序
        Collections.sort(list, (Map.Entry<Character, int[]> entry1, Map.Entry<Character, int[]> entry2)-> {
            //排序   返回负数不交换位置  返回正则会交换
            //从大到小排序  所以若大于返回负数 不交换位置
              for (int i=0;i<candidate;i++){
                  if(entry1.getValue()[i]<entry2.getValue()[i]){
                      return 1;
                  }
                if(entry1.getValue()[i]>entry2.getValue()[i]){
                      return -1;
                  }
              }
              //所有投票相等 按字符顺序
            return entry1.getKey()-entry2.getKey();
        });

        //拼接字符串
        StringBuilder sb=new StringBuilder();
        for (Map.Entry<Character,int[]> enty:list) {

            sb.append(enty.getKey());
        }
        return  sb.toString();
    }
}
