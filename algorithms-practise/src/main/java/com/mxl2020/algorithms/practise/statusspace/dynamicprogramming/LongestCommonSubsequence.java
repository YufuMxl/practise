package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 最长公共子序列
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-common-subsequence/">LeetCode 1143</a>
 */
public class LongestCommonSubsequence {

    /**
     * @param text1 字符串 1
     * @param text2 字符串 2
     * @return 返回字符串 1 和 2 的最长公共子序列的长度
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
