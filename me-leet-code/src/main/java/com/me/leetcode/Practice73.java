package com.me.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *矩阵置零
 *  输入:
 *  [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 *   ]
 * 输出:
 *  [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 *  ]
 * @ClassName Practice73
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/28 11:08
 * @Version 1.0
 **/
public class Practice73 {
    public static void main(String[] args) {
        int[][] matrix={{1,0,3,5},
                        {2,3,0,1},
                        {4,5,2,6}};
       // setZeroes1( matrix);
        setZeroes2( matrix);
        for (int[] arr:matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 找出0的坐标
     * 遍历归0
     * @param matrix
     */
    public static void setZeroes1(int[][] matrix) {
        Set<Integer> set_i=new HashSet<>();
        Set<Integer> set_j=new HashSet<>();
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[0].length ; j++) {
                if(matrix[i][j]==0){
                    set_i.add(i);
                    set_j.add(j);
                    if(set_i.size()==matrix.length||
                            set_j.size()==matrix[0].length     ){
                        break;
                    }
                }
            }
        }

        for (Integer n:set_i) {

            for (int i = 0; i <matrix[0].length ; i++) {
                       matrix[n][i]=0;
            }
        }
        for (Integer n:set_j) {
            for (int i = 0; i <matrix.length ; i++) {
                matrix[i][n]=0;
            }
        }
    }

    /**
     * isLastRowZero 前一行是否为0
     * 在遍历下一行时对前一行进行归0
     * 在遍历当前行时对列进行归0
     * @param matrix
     */
    public static void setZeroes2(int[][] matrix) {
        // 前一行为0标志
        boolean isLastRowZero = false;

        for (int i = 0; i < matrix.length; i ++) {

            // 当前行为0标志,即需要将改行全置为0
            boolean isCurrRowZero = false;

            for (int j = 0; j < matrix[0].length; j ++) {
                if (matrix[i][j] == 0) {
                    isCurrRowZero = true;
                    // 纵向上一个值不是零，说明纵向第一次出现零，需要把纵向前面的值都置为零
                    if (i > 0 && matrix[i - 1][j] != 0) {
                        for (int k = 0; k < i; k ++) {
                            matrix[k][j] = 0;
                        }
                    }
                } else if (i > 0 && matrix[i - 1][j] == 0){
                    // 纵向上一个值如果为零，则把纵向的零延伸到此行
                    matrix[i][j] = 0;
                }

                if (isLastRowZero) {
                    // 如果上一行为零标志为真,
                    // 则上一行这个位置置为零（纵向为零的判断在上面处理过，所以到这里才可以设置为0）
                    matrix[i - 1][j] = 0;
                }
            }






            isLastRowZero = isCurrRowZero;
        }

        // 处理最后一行为零的情况
        if (isLastRowZero) {
            for (int i = 0; i < matrix[0].length; i ++) {
                matrix[matrix.length - 1][i] = 0;
            }
        }

    }
}
