package com.mxl2020.algorithms.practise.search.bfs;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

/**
 * 单词接龙
 *
 * @see <a href="https://leetcode.cn/problems/word-ladder/">LeetCode 127</a>
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        Queue<Pair<String, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(beginWord, 1));
        while (!queue.isEmpty()) {
            Pair<String, Integer> wordPair = queue.poll();
            char[] wordCharArr = wordPair.getKey().toCharArray();
            int depth = wordPair.getValue();

            for (int i = 0; i < wordCharArr.length; i++) {
                char oldChar = wordCharArr[i];
                for (int j = 0; j < 26; j++) {
                    wordCharArr[i] = (char) ('a' + j);
                    String nextWord = String.valueOf(wordCharArr);
                    if (nextWord.equals(endWord)) return depth + 1;
                    else if (wordSet.contains(nextWord)) {
                        queue.offer(new Pair<>(nextWord, depth + 1));
                        wordSet.remove(nextWord);
                    }
                }
                wordCharArr[i] = oldChar;
            }
        }
        return 0;
    }

}
