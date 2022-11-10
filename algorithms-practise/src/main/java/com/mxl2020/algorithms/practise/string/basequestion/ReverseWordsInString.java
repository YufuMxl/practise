package com.mxl2020.algorithms.practise.string.basequestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 反转字符串中的单词
 *
 * @see <a href="https://leetcode.cn/problems/reverse-words-in-a-string/">LeetCode 151</a>
 */
public class ReverseWordsInString {

    public String reverseWords(String s) {
        s += " ";
        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (sb.length() != 0) {
                    words.add(sb.toString());
                    sb.setLength(0);
                }
            } else sb.append(c);
        }

        Collections.reverse(words);
        return String.join(" ", words);
    }
}
