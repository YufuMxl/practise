package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 最长回文子序列
 *
 * @see <a href="https://leetcode.cn/problems/longest-palindromic-subsequence/">LeetCode 516</a>
 */
public class LongestPalindromicSubsequence {

    /**
     * @param s 字符串
     * @return 找出其中最长的回文子序列，并返回该序列的长度
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] opt = new int[n][n];
        for (int i = 0; i < n; i++) {
            opt[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l <= n - len; l++) {
                int r = l + len - 1;
                if (s.charAt(l) == s.charAt(r)) opt[l][r] = opt[l + 1][r - 1] + 2;
                else opt[l][r] = Math.max(opt[l + 1][r], opt[l][r - 1]);
            }
        }
        return opt[0][n - 1];
    }
}
