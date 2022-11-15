package com.mxl2020.algorithms.practise.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到字符串中所有字母异位词
 *
 * @see <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/">LeetCode 438</a>
 */
public class FindAllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        String pKey = genKey(p);

        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            String subS = s.substring(i, i + p.length());
            if (pKey.equals(genKey(subS))) ans.add(i);
        }

        return ans;
    }

    private String genKey(String str) {
        int[] arr = new int[26];
        for (char c : str.toCharArray()) {
            arr[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) sb.append((char)(i + 'a')).append(arr[i]);
        }
        return sb.toString();
    }
}
