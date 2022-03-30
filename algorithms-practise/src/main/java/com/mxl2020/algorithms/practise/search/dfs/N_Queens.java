package com.mxl2020.algorithms.practise.search.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N 皇后
 *
 * @see <a href="https://leetcode-cn.com/problems/n-queens/">LeetCode 51</a>
 */
public class N_Queens {

    /**
     * 要求将 n 个皇后放置在 n * n 的棋盘上，且不存在攻击线路
     * <p>
     * 皇后会直线、对角线攻击，所以同一条直线、对角线不能有 1 个以上的皇后
     *
     * @return 返回 n 个皇后在棋盘中的所有摆放
     */
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.chessboard = new char[n][n];
        for (char[] row : chessboard) {
            Arrays.fill(row, '.');
        }
        this.combinations = new ArrayList<>();
        solve(0);
        return combinations;
    }

    private int n;
    private char[][] chessboard;
    private List<List<String>> combinations;

    private void solve(int queenNum) {
        if (queenNum == n) {
            List<String> combination = new ArrayList<>(n);
            for (char[] row : chessboard) {
                combination.add(new String(row));
            }
            combinations.add(combination);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!availablePosition(queenNum, i)) continue;
            chessboard[queenNum][i] = 'Q';
            solve(queenNum + 1);
            chessboard[queenNum][i] = '.';
        }
    }

    private boolean availablePosition(int x, int y) {
        for (int i = 0; i < x; i++) {
            // 判断当前列是否有皇后
            if (chessboard[i][y] == 'Q') return false;
            // 判断 \ 对角线是否有皇后
            int leftDiagonalIndex = y - x + i;
            if (leftDiagonalIndex >= 0 && chessboard[i][leftDiagonalIndex] == 'Q') return false;
            // 判断 / 对角线是否有皇后
            int rightDiagonalIndex = y + x - i;
            if (rightDiagonalIndex < n && chessboard[i][rightDiagonalIndex] == 'Q') return false;
        }
        return true;
    }

}
