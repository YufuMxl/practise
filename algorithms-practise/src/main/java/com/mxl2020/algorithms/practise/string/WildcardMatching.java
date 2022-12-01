package com.mxl2020.algorithms.practise.string;

/**
 * 通配符匹配
 *
 * @see <a href="https://leetcode.cn/problems/wildcard-matching/">LeetCode 44</a>
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') f[0][j] = true;
            else break;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pChar = p.charAt(j - 1);
                if (pChar == '*') {
                    f[i][j] = f[i - 1][j - 1] || f[i][j - 1] || f[i - 1][j];
                } else if (pChar == '?') {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    if (s.charAt(i - 1) == pChar) f[i][j] = f[i - 1][j - 1];
                }
            }
        }

        return f[m][n];
    }
}
