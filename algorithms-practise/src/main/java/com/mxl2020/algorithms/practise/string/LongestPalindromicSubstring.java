package com.mxl2020.algorithms.practise.string;

/**
 * 最长回文子串
 *
 * @see <a href="https://leetcode.cn/problems/longest-palindromic-substring/">LeetCode 5</a>
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        int n = s.length();

        int beginIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < n; i++) {
            int left = i;
            int right = i;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if ((right - left - 2) > (endIndex - beginIndex)) {
                beginIndex = left + 1;
                endIndex = right - 1;
            }
        }

        for (int i = 1; i < n; i++) {
            int left = i - 1;
            int right = i;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if ((right - left - 2) > (endIndex - beginIndex)) {
                beginIndex = left + 1;
                endIndex = right - 1;
            }
        }

        return s.substring(beginIndex, endIndex + 1);
    }
}
