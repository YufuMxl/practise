package com.mxl2020.algorithms.practise.string.basequestion;

import java.util.HashMap;
import java.util.Map;

/**
 * 同构字符串
 *
 * @see <a href="https://leetcode.cn/problems/isomorphic-strings/">LeetCode 205</a>
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            char y = t.charAt(i);

            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) return false;
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }
}
