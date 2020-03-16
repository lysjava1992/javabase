package com.me.leetcode;

/**
 *  最长公共前缀
 * ["flower","flow","flight"]   "fl"
 * ["dog","racecar","car"]      ""
 * @ClassName Practice14
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 9:48
 * @Version 1.0
 **/
public class Practice14 {
    public static void main(String[] args) {
        String[] strings={"flower","flow","flight"};
        System.out.println("公共前缀Method1： "+longestCommonPrefix1(strings));
        System.out.println("公共前缀Method2： "+longestCommonPrefix2(strings));
        System.out.println("公共前缀Method3： "+longestCommonPrefix3(strings));

    }

    /**
     * 假设第一个字符串就是最长前缀
     * 比较 截取字符串
     * @param strs
     * @return
     */
    private static String longestCommonPrefix3(String[] strs) {
        if(strs.length<=0||strs[0].length()<=0){return  "";}
        String result=strs[0];
        //外层循环控制比较的两个字符串
        for (int i=1;i<strs.length;i++){

            //内层循环 比较  j<result.length() 不能超出现有公共字符串长度
            int j=0;
            for (;j<strs[i].length()&&j<result.length();j++){
                if(strs[i].charAt(j)!=result.charAt(j)){
                    break;
                }
            }
            result=result.substring(0,j);
        }

        return result;
    }

    /**
     * 遍历查询
     * @param strs
     * @return
     */
    private static String longestCommonPrefix2(String[] strs) {
        if(strs.length<=0||strs[0].length()<=0){return "";}
        StringBuilder sb=new StringBuilder("");
        for (int i=0;i<strs[0].length();i++){
            char temp=strs[0].charAt(i);
            for (int j=1;j<strs.length;j++){
                if(strs[j].length()<=i||strs[j].charAt(i)!=temp){
                    return sb.toString();
                }
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 耗时 耗资源
     * @param strs
     * @return
     */
    public static String longestCommonPrefix1(String[] strs) {
        if(strs.length<=0||strs[0].length()<=0){return "";}
        StringBuilder sb=new StringBuilder("");
        int index=0;
        char  temp=strs[index].charAt(index);
        while (true){
            for (int i=0;i<strs.length;i++){
                if(strs[i].length()>index){
                     temp=strs[0].charAt(index);
                    if(strs[i].charAt(index)!=temp){
                        return sb.toString();
                    }

                }else {
                    return sb.toString();
                }

            }
            sb.append(temp);
            index++;
        }

    }
}
