package com.mxl2020.algorithms.practise.string.basequestion;

/**
 * 验证回文串
 *
 * @see <a href="https://leetcode.cn/problems/valid-palindrome/">LeetCode 125</a>
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (!isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            if (!isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;

            left++;
            right--;
        }

        return true;
    }

    private boolean isLetterOrDigit(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
