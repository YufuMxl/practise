package com.mxl2020.algorithms.practise.string.basequestion;

/**
 * 最长公共前缀
 *
 * @see <a href="https://leetcode.cn/problems/longest-common-prefix/description/">LeetCode 14</a>
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        int minLength = (int) 1e9;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            char c = strs[0].charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != c) return sb.toString();
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
