package com.mxl2020.algorithms.practise.string.basequestion;

import java.util.*;

/**
 * 字母异位词分组
 *
 * @see <a href="https://leetcode-cn.com/problems/group-anagrams/">LeetCode 49</a>
 */
public class GroupAnagrams {

    /**
     * 对每个字符串进行排序，以排序后的字符串作为 key 存入哈希表
     * <p>
     * 时间复杂度 O(n * k * log k)
     * 空间复杂度 O(n)
     *
     * @param strs 字母异位词数组
     * @return 分组的字母异位词
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] strCharArray = str.toCharArray();
            Arrays.sort(strCharArray);
            String strKey = new String(strCharArray);
            if (map.get(strKey) != null) {
                map.get(strKey).add(str);
            } else {
                List<String> anagrams = new ArrayList<>();
                anagrams.add(str);
                map.put(strKey, anagrams);
            }
        }

        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = genKey(str);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

    private String genKey(String str) {
        int[] arr = new int[26];
        for (char c : str.toCharArray()) {
            arr[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) sb.append(i + 'a').append(arr[i]);
        }
        return sb.toString();
    }

}
