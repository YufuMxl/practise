package com.mxl2020.algorithms.practise.string.basequestion;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符
 *
 * @see <a href="https://leetcode.cn/problems/first-unique-character-in-a-string/">LeetCode 387</a>
 */
public class FirstUniqueCharacterInString {

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }

    public int firstUniqChar2(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.get(c) == null) map.put(c, i);
            else map.put(c, -1);
        }
        int first = n;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != -1 && first > entry.getValue()) {
                first = entry.getValue();
            }
        }
        return first == n ? -1 : first;
    }
}
