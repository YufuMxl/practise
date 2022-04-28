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
        final int m = text1.length();
        final int n = text2.length();
        // 确定状态 & 最优子结构：设 opt[i][j] 表示 text1 前 i 个字符与 text2 前 j 个字符的 LCS
        int[][] opt = new int[m + 1][n + 1];
        // 记录转移路径
        int[][] pre = new int[m + 1][n + 1];

        for (int i = 1; i < opt.length; i++) {
            for (int j = 1; j < opt[0].length; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) opt[i][j] = opt[i - 1][j - 1] + 1;
                else {
                    if (opt[i][j - 1] > opt[i - 1][j]) {
                        opt[i][j] = opt[i][j - 1];
                        pre[i][j] = 1;
                    } else {
                        opt[i][j] = opt[i - 1][j];
                        pre[i][j] = 2;
                    }
                }
            }
        }
        print(text1, pre, m, n);
        return opt[m][n];
    }

    private void print(final String text1, final int[][] pre, final int i, final int j) {
        if (i == 0 || j == 0) return;
        if (1 == pre[i][j]) print(text1, pre, i, j - 1);
        else if (2 == pre[i][j]) print(text1, pre, i - 1, j);
        else {
            print(text1, pre, i - 1, j - 1);
            System.out.println(text1.charAt(i - 1));
        }
    }
}
