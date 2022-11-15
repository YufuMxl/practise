package com.mxl2020.algorithms.practise.string.basequestion;

/**
 * 有效的字母异位词
 *
 * @see <a href="https://leetcode.cn/problems/valid-anagram/">LeetCode 242</a>
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] arr = new int[26];

        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            arr[c - 'a']--;
        }

        for (int count : arr) {
            if (count != 0) return false;
        }

        return true;
    }
}
