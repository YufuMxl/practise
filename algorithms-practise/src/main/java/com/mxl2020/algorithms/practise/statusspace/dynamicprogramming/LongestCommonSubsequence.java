package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 最长公共子序列 LCS
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
        // 确定状态 & 最优子结构：设 opt[i][j] 表示 text1 前 i 个字符与 text2 前 j 个字符的 LCS
        int[][] opt = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i < opt.length; i++) {
            for (int j = 1; j < opt[0].length; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) opt[i][j] = opt[i - 1][j - 1] + 1;
                else opt[i][j] = Math.max(opt[i][j - 1], opt[i - 1][j]);
            }
        }

        return opt[opt.length - 1][opt[0].length - 1];
    }
}
