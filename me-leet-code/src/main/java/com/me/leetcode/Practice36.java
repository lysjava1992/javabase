package com.me.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 有效的数据
 * 9X9
 * 1-9和.
 * @author: Mr.Luan
 * @create: 2020-03-20 15:28
 **/
public class Practice36 {
    public static void main(String[] args) {
        char[][] board = {
                {'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '4', '.', '.', '.', '.', '.', '.'}
        };
        System.out.println(isValidSudoku1(board));
        System.out.println(isValidSudoku2(board));
    }

    /**
     * 81个数遍历一遍
     *
     * @param board
     * @return
     */
    private static boolean isValidSudoku2(char[][] board) {
        Set<Character>[] rows = new HashSet[9];
        Set<Character> [] columns = new HashSet[9];
        Set<Character> [] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            columns[i] = new HashSet();
            boxes[i] = new HashSet();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                  //属于第几个盒子
                   int boxIndex=(i/3)*3+j/3;
                   //第i行 第j列
                   if(board[i][j]=='.'){continue;}
                   if(rows[i].contains(board[i][j])||columns[j].contains(board[i][j])||boxes[boxIndex].contains(board[i][j])){
                       return false;
                   }
                rows[i].add(board[i][j]);
                columns[j].add(board[i][j]);
                boxes[boxIndex].add(board[i][j]);
            }
        }
        return true;
    }

    /**
     * 每一行
     * 每一列
     * 9个矩形
     * @param board
     * @return
     */
    public static boolean isValidSudoku1(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> sets1 = new HashSet<>();
            Set<Character> sets2 = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (sets1.contains(board[i][j]) || sets2.contains(board[j][i])) {
                    return false;
                }
                if (!sets1.contains(board[i][j]) && board[i][j] != '.') {
                    sets1.add(board[i][j]);
                }
                if (!sets2.contains(board[j][i]) && board[j][i] != '.') {
                    sets2.add(board[j][i]);
                }
            }
        }
        for (int i = 0; i < 9; i = i + 3) {
            for (int j = 0; j < 9; j = j + 3) {
                Set<Character> sets1 = new HashSet<>();
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        if (board[i+m][j+n] == '.') {
                        } else if (sets1.contains(board[i+m][j+n])) {
                            return false;
                        } else {
                            sets1.add(board[i+m][j+n]);
                        }
                    }
                }
            }
        }
        return true;
    }
}
