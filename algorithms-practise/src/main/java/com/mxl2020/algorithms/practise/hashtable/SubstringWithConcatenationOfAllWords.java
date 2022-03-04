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
        // 单词长度
        int wordLength = words[0].length();
        // 所有单词拼接起来的长度
        int totalWordsLength = wordLength * words.length;
        // 统计单词数组的每个单词出现的次数
        Map<String, Integer> wordToCountMap = genWordToCountMap(words);

        List<Integer> result = new ArrayList<>();
        for (int j = 0; j < wordLength; j++) {
            // TODO 同一层的 substring 共用一个 map
            for (int i = j; i < s.length() - totalWordsLength + 1; i += wordLength) {
                String substring = s.substring(i, i + totalWordsLength);
                if (isValidSubstring(substring, wordToCountMap, wordLength)) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    private boolean isValidSubstring(String substring, Map<String, Integer> wordToCountMap, int wordLength) {
        Map<String, Integer> substringMap = genWordToCountMap(substring, wordLength);
        return isSameMap(substringMap, wordToCountMap);
    }

    private Map<String, Integer> genWordToCountMap(String[] words) {
        Map<String, Integer> wordToCountMap = new HashMap<>();
        for (String word : words) {
            if (wordToCountMap.containsKey(word)) {
                wordToCountMap.put(word, wordToCountMap.get(word) + 1);
            } else {
                wordToCountMap.put(word, 1);
            }
        }
        return wordToCountMap;
    }

    private Map<String, Integer> genWordToCountMap(String substring, int wordLength) {
        String[] words = new String[substring.length() / wordLength];
        for (int i = 0; i < words.length; i++) {
            words[i] = substring.substring(i * wordLength, (i + 1) * wordLength);
        }
        return genWordToCountMap(words);
    }

    private boolean isSameMap(Map<String, Integer> a, Map<String, Integer> b) {
        for (String key : a.keySet()) {
            if (!Objects.equals(a.get(key), b.get(key))) {
                return false;
            }
        }
        for (String key : b.keySet()) {
            if (!Objects.equals(a.get(key), b.get(key))) {
                return false;
            }
        }
        return true;
    }
}
