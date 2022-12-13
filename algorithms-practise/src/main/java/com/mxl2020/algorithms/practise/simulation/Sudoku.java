package com.mxl2020.algorithms.practise.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效的数独 + 解数独
 *
 * @see <a href="https://leetcode.cn/problems/valid-sudoku/">LeetCode 36</a><a href="https://leetcode.cn/problems/sudoku-solver/">LeetCode 37</a>
 */
public class Sudoku {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;

                int cIndex = c - '1';
                if (row[i][cIndex]) return false;
                else row[i][cIndex] = true;

                if (col[j][cIndex]) return false;
                else col[j][cIndex] = true;

                int boxNum = i / 3 * 3 + j / 3;
                if (box[boxNum][cIndex]) return false;
                else box[boxNum][cIndex] = true;
            }
        }
        return true;
    }


    boolean[][] rowUsed = new boolean[9][10];
    boolean[][] colUsed = new boolean[9][10];
    boolean[][] boxUsed = new boolean[9][10];

    private char[][] board;
    private final List<int[]> emptyCells = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    emptyCells.add(new int[]{i, j});
                    continue;
                }

                int num = board[i][j] - '0';
                rowUsed[i][num] = true;
                colUsed[j][num] = true;
                boxUsed[(i / 3) * 3 + (j / 3)][num] = true;
            }
        }
        dfs(0);
    }

    private boolean dfs(int index) {
        if (index == emptyCells.size()) return true;

        int x = emptyCells.get(index)[0];
        int y = emptyCells.get(index)[1];

        List<Integer> validNumbers = find(x, y);
        for (int num : validNumbers) {
            int boxNum = (x / 3) * 3 + (y / 3);
            board[x][y] = (char) (num + '0');
            rowUsed[x][num] = true;
            colUsed[y][num] = true;
            boxUsed[boxNum][num] = true;

            if (dfs(index + 1)) return true;

            rowUsed[x][num] = false;
            colUsed[y][num] = false;
            boxUsed[boxNum][num] = false;
        }

        board[x][y] = '.';
        return false;
    }

    private List<Integer> find(int x, int y) {
        int boxNum = (x / 3) * 3 + (y / 3);
        List<Integer> validNumbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (!rowUsed[x][i] && !colUsed[y][i] && !boxUsed[boxNum][i]) validNumbers.add(i);
        }
        return validNumbers;
    }

}
