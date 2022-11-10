package com.mxl2020.algorithms.practise.string.basequestion;

/**
 * 仅仅反转字母
 *
 * @see <a href="https://leetcode.cn/problems/reverse-only-letters/">LeetCode 917</a>
 */
public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String s) {
        char[] charArray = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (!isLetter(charArray[start])) start++;
            else if (!isLetter(charArray[end])) end--;
            else {
                char tmp = charArray[start];
                charArray[start++] = charArray[end];
                charArray[end--] = tmp;
            }
        }
        return new String(charArray);
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
