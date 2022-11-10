package com.mxl2020.algorithms.practise.string;

/**
 * 字符串转换整数 (atoi)
 *
 * @see <a href="https://leetcode.cn/problems/string-to-integer-atoi/">LeetCode 8</a>
 */
public class StringToIntegerATOI {

    public int myAtoi(String s) {
        s = s.trim();
        int sign = 1;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' && i == 0) continue;
            if (c == '-' && i == 0) {
                sign = -1;
                continue;
            }
            if (c < '0' || c > '9') break;

            if ((Integer.MAX_VALUE - (c - '0')) / 10 < ans) {
                if (sign == 1) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            } else ans = (ans * 10 + (c - '0'));
        }
        return sign * ans;
    }
}
