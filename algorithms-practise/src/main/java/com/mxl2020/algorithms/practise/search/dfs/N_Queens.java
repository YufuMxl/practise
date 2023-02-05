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
     * 皇后会直线、对角线攻击，所以同一条直线、对角线的皇后个数只能有 1 个
     *
     * @return 返回所有可能的摆放方式
     */
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.chessboard = new char[n][n];
        for (char[] row : chessboard) Arrays.fill(row, '.');
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
            for (char[] row : chessboard) combination.add(new String(row));
            combinations.add(combination);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (availablePosition(queenNum, i)) {
                chessboard[queenNum][i] = 'Q';
                solve(queenNum + 1);
                chessboard[queenNum][i] = '.';
            }
        }
    }

    private boolean availablePosition(int x, int y) {
        for (int i = 0; i < x; i++) {
            // 判断当前列是否有皇后
            if (chessboard[i][y] == 'Q') return false;
        }

        for (int i = 1; i <= x; i++) {
            // 判断同一斜线是否有皇后
            if ((x - i) >= 0 && (y - i) >= 0 && chessboard[x - i][y - i] == 'Q') return false;
            if ((x - i) >= 0 && (y + i) < n && chessboard[x - i][y + i] == 'Q') return false;
        }
        return true;
    }

}
