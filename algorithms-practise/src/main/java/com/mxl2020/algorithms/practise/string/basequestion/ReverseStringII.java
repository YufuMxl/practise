package com.mxl2020.algorithms.practise.string.basequestion;

/**
 * 反转字符串 II
 *
 * @see <a href="https://leetcode.cn/problems/reverse-string-ii/">LeetCode 541</a>
 */
public class ReverseStringII {

    public String reverseStr(String s, int k) {
        int n = s.length();
        int groupCount = (n + k - 1) / k;
        StringBuilder sb = new StringBuilder();
        for (int groupNum = 0; groupNum < groupCount; groupNum++) {
            int startIndex = groupNum * k;
            int endIndex = Math.min(startIndex + k - 1, n - 1);
            if (groupNum % 2 == 0) {
                for (int i = endIndex; i >= startIndex; i--) {
                    sb.append(s.charAt(i));
                }
            } else {
                for (int i = startIndex; i <= endIndex; i++) {
                    sb.append(s.charAt(i));
                }
            }
        }
        return sb.toString();
    }

}
