package com.mxl2020.algorithms.practise.hashtable;

import java.util.*;

/**
 * 串联所有单词的子串
 *
 * @see <a href="https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/">LeetCode 30</a>
 */
public class SubstringWithConcatenationOfAllWords {

    /**
     * @param s     一串长字符串
     * @param words 单词数组
     * @return 从长字符串中找到子串的起始下标，使得子串可以由单词数组的所有单词拼接而成
     */
    public List<Integer> findSubstring(String s, String[] words) {
        final Map<String, Integer> wordToCountMap = new HashMap<>();
        for (String word : words) wordToCountMap.put(word, wordToCountMap.getOrDefault(word, 0) + 1);

        final int wordLength = words[0].length();
        final int totalWordsLength = wordLength * words.length;
        final List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < wordLength; i++) {
            int endIndex = i + totalWordsLength;
            if (endIndex > s.length()) break;
            // 计算第一个 subStr 的 count map
            Map<String, Integer> countMap = new HashMap<>();
            String subStr = s.substring(i, endIndex);
            for (int j = 0; j < words.length; j++) {
                String word = subStr.substring(j * wordLength, (j + 1) * wordLength);
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
            if (isSameMap(wordToCountMap, countMap)) ans.add(i);

            // 计算之后的 subStr 的 count map
            for (int j = endIndex; j <= s.length() - wordLength; j += wordLength) {
                int subStrStartIndex = j - totalWordsLength;
                String outWord = s.substring(subStrStartIndex, subStrStartIndex + wordLength);
                String inWord = s.substring(j, j + wordLength);
                if (!outWord.equals(inWord)) {
                    countMap.put(outWord, countMap.get(outWord) - 1);
                    countMap.put(inWord, countMap.getOrDefault(inWord, 0) + 1);
                }
                if (isSameMap(wordToCountMap, countMap)) ans.add(subStrStartIndex + wordLength);
            }
        }

        return ans;
    }

    private boolean isSameMap(Map<String, Integer> aMap, Map<String, Integer> bMap) {
        for (String key : aMap.keySet()) {
            if (aMap.get(key) == 0) continue;
            if (!Objects.equals(aMap.get(key), bMap.get(key))) return false;
        }
        for (String key : bMap.keySet()) {
            if (bMap.get(key) == 0) continue;
            if (!Objects.equals(aMap.get(key), bMap.get(key))) return false;
        }
        return true;
    }
}
