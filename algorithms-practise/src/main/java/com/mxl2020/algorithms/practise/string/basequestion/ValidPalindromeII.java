package com.mxl2020.algorithms.practise.string.basequestion;

/**
 * 验证回文串 II
 *
 * @see <a href="https://leetcode.cn/problems/valid-palindrome-ii/">LeetCode 680</a>
 */
public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1, 1);
    }

    private boolean validPalindrome(String s, int start, int end, int deleteCount) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                if (deleteCount > 0)
                    return validPalindrome(s, start + 1, end, deleteCount - 1) || validPalindrome(s, start, end - 1, deleteCount - 1);
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
