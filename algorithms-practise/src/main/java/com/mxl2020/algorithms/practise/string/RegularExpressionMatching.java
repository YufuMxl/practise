package com.mxl2020.algorithms.practise.string;

/**
 * 正则表达式匹配
 *
 * @see <a href="https://leetcode.cn/problems/regular-expression-matching/">LeetCode 10</a>
 */
public class RegularExpressionMatching {
    // * 和它前面的一个字符是一个整体，表示 0 或 n 个 preChar
    // . 表示任意一个字符
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        // 特殊情况处理：a*b*c* 可以表示为一个空字符串
        for (int j = 2; j <= n; j += 2) {
            if (p.charAt(j - 1) == '*') f[0][j] = true;
            else break;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);
                if (pChar == '.') {
                    f[i][j] = f[i - 1][j - 1];
                } else if (pChar == '*') {
                    if (sChar != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        f[i][j] = f[i][j - 2];
                    } else {
                        f[i][j] = f[i][j - 2] || f[i - 1][j];
                    }
                } else {
                    if (sChar == pChar) f[i][j] = f[i - 1][j - 1];
                }
            }
        }

        return f[m][n];
    }
}
