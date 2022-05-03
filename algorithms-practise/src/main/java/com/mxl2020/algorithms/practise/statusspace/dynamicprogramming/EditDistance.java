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
        int[][] opt = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i < opt.length; i++) {
            for (int j = 0; j < opt[0].length; j++) {
                if (i == 0 || j == 0) opt[i][j] = i + j;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1)) opt[i][j] = opt[i - 1][j - 1];
                else opt[i][j] = Math.min(opt[i - 1][j - 1] + 1, Math.min(opt[i - 1][j] + 1, opt[i][j - 1] + 1));
            }
        }

        return opt[word1.length()][word2.length()];
    }
}
