package com.mxl2020.algorithms.practise.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到字符串中所有字母异位词
 *
 * @see <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/">LeetCode 438</a>
 */
public class FindAllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) return ans;

        // 计算 p 的 key
        int[] arr = new int[26];
        for (char c : p.toCharArray()) {
            arr[c - 'a']++;
        }
        String pKey = genKey(arr);

        // 计算 s 的 key
        Arrays.fill(arr, 0);
        for (char c : s.substring(0, p.length()).toCharArray()) {
            arr[c - 'a']++;
        }
        if (pKey.equals(genKey(arr))) ans.add(0);

        for (int l = 1; l < s.length() - p.length() + 1; l++) {
            int r = l + p.length() - 1;
            if (s.charAt(l - 1) == s.charAt(r)) {
                if (!ans.isEmpty() && ans.get(ans.size() - 1) == l - 1) ans.add(l);
                continue;
            }
            arr[s.charAt(l - 1) - 'a']--;
            arr[s.charAt(r) - 'a']++;
            if (pKey.equals(genKey(arr))) ans.add(l);
        }

        return ans;
    }

    private final StringBuilder sb = new StringBuilder();

    private String genKey(int[] arr) {
        this.sb.setLength(0);
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) this.sb.append((char) (i + 'a')).append(arr[i]);
        }
        return this.sb.toString();
    }
}
