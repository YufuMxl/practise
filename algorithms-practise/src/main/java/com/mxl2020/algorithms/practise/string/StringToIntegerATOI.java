package com.mxl2020.algorithms.practise.string;

/**
 * 字符串转换整数 (atoi)
 *
 * @see <a href="https://leetcode.cn/problems/string-to-integer-atoi/">LeetCode 8</a>
 */
public class StringToIntegerATOI {

    public int myAtoi(String s) {
        // 1.忽略前导空格
        s = s.trim();
        // 2.正负号
        int sign = 1;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' && i == 0) continue;
            if (c == '-' && i == 0) {
                sign = -1;
                continue;
            }

            // 3.处理到非数字结束
            if (c < '0' || c > '9') break;
            // 4.处理数字直到末尾
            if (ans > (Integer.MAX_VALUE - (c - '0')) / 10) {
                if (sign == 1) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }
            ans = ans * 10 + (c - '0');
        }
        return sign * ans;
    }
}
