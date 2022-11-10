package com.mxl2020.algorithms.practise.string.basequestion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 反转字符串中的单词 III
 *
 * @see <a href="https://leetcode.cn/problems/reverse-words-in-a-string-iii/">LeetCode 557</a>
 */
public class ReverseWordsInStringIII {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();

        List<String> words = Arrays.asList(sb.toString().split(" "));
        Collections.reverse(words);
        return String.join(" ", words);
    }
}
