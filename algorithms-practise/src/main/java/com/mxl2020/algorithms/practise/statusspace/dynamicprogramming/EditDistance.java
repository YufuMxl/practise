package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 编辑距离
 *
 * @see <a href="https://leetcode-cn.com/problems/edit-distance/">LeetCode 72</a>
 */
public class EditDistance {

    /**
     * @param word1 字符串 1
     * @param word2 字符串 2
     * @return 返回将 1 变成 2 的最小编辑次数（编辑方式有插入、删除、替换一个字符）
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0) dp[i][j] = i + j;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
