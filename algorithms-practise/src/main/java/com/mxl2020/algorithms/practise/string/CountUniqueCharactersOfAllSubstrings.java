package com.mxl2020.algorithms.practise.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计子串中的唯一字符
 *
 * @see <a href="https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string/">LeetCode 828</a>
 */
public class CountUniqueCharactersOfAllSubstrings {

    public int uniqueLetterString(String s) {
        Map<Character, List<Integer>> charToIndexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!charToIndexMap.containsKey(c)) {
                charToIndexMap.put(c, new ArrayList<>());
                charToIndexMap.get(c).add(-1);
            }
            charToIndexMap.get(c).add(i);
        }

        int ans = 0;
        for (Map.Entry<Character, List<Integer>> charToIndices : charToIndexMap.entrySet()) {
            List<Integer> indices = charToIndices.getValue();
            indices.add(s.length());
            for (int i = 1; i < indices.size() - 1; i++) {
                ans += (indices.get(i) - indices.get(i - 1)) * (indices.get(i + 1) - indices.get(i));
            }
        }
        return ans;
    }
}
