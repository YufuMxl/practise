package com.mxl2020.algorithms.practise.simulation;

import javafx.util.Pair;

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

    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;

                int num = board[i][j] - '0';
                rowUsed[i][num] = true;
                colUsed[j][num] = true;
                boxUsed[(i / 3) * 3 + (j / 3)][num] = true;
            }
        }
        dfs();
    }

    private boolean dfs() {
        Pair<int[], List<Integer>> emptyCell = findEmptyCell();
        if (emptyCell == null) return true;

        int x = emptyCell.getKey()[0];
        int y = emptyCell.getKey()[1];

        List<Integer> availableDigits = emptyCell.getValue();
        for (int num : availableDigits) {
            board[x][y] = (char) (num + '0');
            rowUsed[x][num] = true;
            colUsed[y][num] = true;
            boxUsed[(x / 3) * 3 + (y / 3)][num] = true;

            if (dfs()) return true;

            rowUsed[x][num] = false;
            colUsed[y][num] = false;
            boxUsed[(x / 3) * 3 + (y / 3)][num] = false;
        }

        board[x][y] = '.';
        return false;
    }

    private Pair<int[], List<Integer>> findEmptyCell() {
        int minValue = 10;
        int[] emptyCell = null;
        List<Integer> availableDigits = null;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') continue;

                List<Integer> tmp = findAvailableDigits(i, j);
                if (tmp.size() < minValue) {
                    minValue = tmp.size();
                    emptyCell = new int[]{i, j};
                    availableDigits = tmp;
                }
            }
        }

        if (emptyCell == null) return null;
        return new Pair<>(emptyCell, availableDigits);
    }

    private List<Integer> findAvailableDigits(int x, int y) {
        int boxNum = (x / 3) * 3 + (y / 3);
        List<Integer> availableDigits = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (!rowUsed[x][i] && !colUsed[y][i] && !boxUsed[boxNum][i]) availableDigits.add(i);
        }
        return availableDigits;
    }
}
