package com.me.leetcode;

import java.util.Arrays;

/**
 *   图形  [
 *                [1,2,3],
 *                [4,5,6],
 *                [7,8,9]
 *             ]
 *
 *   反转  [
 *                [7,4,1],
 *                [8,5,2],
 *                [9,6,3]
 *             ]
 *  矩阵旋转90度  即：  行转成列
 * @ClassName Practice48
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/16 10:09
 * @Version 1.0
 **/
public class Practice48 {
    public static void main(String[] args) {
        int[][] matrix={
                      {1,2,3,4},
                      {5,6,7,8},
                      {9,10,11,12},
                      {13,14,15,16}
                        };
        rotate1( matrix);
        System.out.println("=================================");
        rotate2( matrix);
    }

    /**
     *  对于 arr[i][j] 旋转后 j就是所在的行
     *  对于 arr[j][i] 旋转后 i就是所在的行
     *  所以可以先对矩形进行转置，再在行内进行前后兑换
     * @param matrix
     */
    private static void rotate2(int[][] matrix) {
        int length=matrix.length;
        for (int i = 0; i <length ; i++) {
            for (int j = i; j <length ; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
        for (int i = 0; i <length ; i++) {
            for (int j = 0; j <length/2 ; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[i][length-1-j];
                matrix[i][length-1-j]=temp;
            }
        }
        for (int i = 0; i <matrix.length ; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    /**
     * 需要额外的空间
     * @param matrix
     */
    private static void rotate1(int[][] matrix) {
        int[][] result=new int[matrix.length][matrix.length];
        for (int i=0;i<matrix.length;i++){
            for (int j = 0; j < matrix.length; j++) {
                result[j][matrix.length-1-i]=matrix[i][j];
            }
        }
        matrix=result;
        for (int i = 0; i <matrix.length ; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
