package com.mxl2020.algorithms.practise.string.basequestion;

/**
 * 反转字符串
 *
 * @see <a href="https://leetcode.cn/problems/reverse-string/">LeetCode 344</a>
 */
public class ReverseString {

    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            char tmp = s[l];
            s[l++] = s[r];
            s[r--] = tmp;
        }
    }
}
