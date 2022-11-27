package com.mxl2020.algorithms.practise.string;

/**
 * 不同的子序列
 *
 * @see <a href="https://leetcode.cn/problems/distinct-subsequences/">LeetCode 115</a>
 */
public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] count = new int[m][n];
        if (s.charAt(0) == t.charAt(0)) count[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (s.charAt(i) == t.charAt(0)) count[i][0] = count[i - 1][0] + 1;
            else count[i][0] = count[i - 1][0];
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s.charAt(i) != t.charAt(j)) count[i][j] = count[i - 1][j];
                else count[i][j] = count[i - 1][j - 1] + count[i - 1][j];
            }
        }

        return count[m - 1][n - 1];
    }
}
