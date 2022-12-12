package com.mxl2020.algorithms.practise.simulation;

/**
 * 有效的数独 +  解数独
 *
 * @see <a href="https://leetcode.cn/problems/valid-sudoku/">LeetCode 36</a><a href="https://leetcode.cn/problems/sudoku-solver/">LeetCode 37</a>
 */
public class Sudoku {

    public boolean isValidSudoku(char[][] board) {
        char[][] row = new char[9][9];
        char[][] col = new char[9][9];
        char[][] box = new char[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;

                int cIndex = c - '1';
                if (row[i][cIndex] != 0) return false;
                else row[i][cIndex] = c;

                if (col[j][cIndex] != 0) return false;
                else col[j][cIndex] = c;

                int boxNum = i / 3 * 3 + j / 3;
                if (box[boxNum][cIndex] != 0) return false;
                else box[boxNum][cIndex] = c;
            }
        }
        return true;
    }

}
