package com.mxl2020.algorithms.practise.string;

/**
 * 转换成小写字母
 *
 * @see <a href="https://leetcode.cn/problems/to-lower-case/">LeetCode 709</a>
 */
public class ToLowerCase {

    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        int a = 'a' - 'A';
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') c += a;
            sb.append(c);
        }
        return sb.toString();
    }
}
