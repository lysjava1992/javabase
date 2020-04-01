package com.me.leetcode;

/**
 * @description: 最长回文子串
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * @author: Mr.Luan
 * @create: 2020-04-01 15:14
 **/
public class Practice5 {
    public static void main(String[] args) {
    System.out.println(longestPalindrome1("bb"));
      System.out.println(longestPalindrome2("bb"));

    }

    /**
     * 中心扩散
     * 遍历 将每一个都当做回文字符串的中心验证
     *
     * @param s
     * @return
     */
    private static String longestPalindrome2(String s) {
        if (s.length() <2) {
            return s;
        }

        String res = s.substring(0, 1);
        for (int i = 0; i < s.length()-1 ; i++) {
           String temp1= palindromicSubstring(s, i,i);
           String temp2=palindromicSubstring(s,i,i+1);
            temp1=temp1.length()>temp2.length()?temp1:temp2;
            res=temp1.length()>res.length()?temp1:res;
        }
        return res;
    }


    private static String palindromicSubstring(String s, int left, int right) {
        while (left>=0&&right<=s.length()-1){
            if(s.charAt(left)!=s.charAt(right)){
                break;
            }
            left--;
            right++;
        }
        //恰好临界点
         if(left<0||right==s.length()){
             left++;
             right--;
         }
        if (s.charAt(left)!=s.charAt(right)){
            left++;
            right--;
        }
         return  s.substring(left,right+1);
    }

    /**
     * 暴力破解
     * s(i j) 所有子字符串
     * 跳过长度小于maxLen
     * 一一验证
     *
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int maxLen = 1;
        String res = s.substring(0, 1);

        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (j - i + 1 > maxLen) {
                    if (validate(s, i, j)) {
                        maxLen = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }

    private static boolean validate(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
