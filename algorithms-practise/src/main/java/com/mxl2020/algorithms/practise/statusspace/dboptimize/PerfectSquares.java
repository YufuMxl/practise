package com.mxl2020.algorithms.practise.statusspace.dboptimize;

import java.util.Arrays;

/**
 * 完全平方数
 *
 * @see <a href="https://leetcode.cn/problems/perfect-squares/">LeetCode 279</a>
 */
public class PerfectSquares {

    /**
     * @param n 将完全平方数看成物品，数值为体积，价值为 1；背包总体积为 n
     * @return 和为 n 的完全平方数的最少数量（最少价值）
     */
    public int numSquares(int n) {
        int m = 0;
        do m++;
        while (m * m <= n);

        int[][] opt = new int[m + 1][n + 1];
        Arrays.fill(opt[0], (int) 1e9);

        for (int i = 1; i <= m; i++) {
            int square = i * i;
            for (int j = 1; j <= n; j++) {
                // 不包含
                opt[i][j] = opt[i - 1][j];
                // 包含 1 次或 1 次以上
                if (j >= square) opt[i][j] = Math.min(opt[i][j], opt[i][j - square] + 1);
            }
        }
        return opt[m][n];
    }

    /**
     * 空间优化
     */
    public int numSquares2(int n) {
        int m = 0;
        do m++;
        while (m * m <= n);

        int[] opt = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            opt[i] = i;
        }

        for (int i = 2; i <= m; i++) {
            int square = i * i;
            for (int j = 1; j <= n; j++) {
                if (j >= square) opt[j] = Math.min(opt[j], opt[j - square] + 1);
            }
        }
        return opt[n];
    }
}

