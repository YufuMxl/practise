package com.mxl2020.algorithms.practise.string.basequestion;

/**
 * 最后一个单词的长度
 *
 * @see <a href="https://leetcode.cn/problems/length-of-last-word/">LeetCode 58</a>
 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int ans = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') ans++;
            else if (ans != 0) break;
        }
        return ans;
    }
}
