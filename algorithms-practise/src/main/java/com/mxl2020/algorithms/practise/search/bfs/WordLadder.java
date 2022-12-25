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
            Pair<String, Integer> word = queue.poll();
            char[] wordCharArr = word.getKey().toCharArray();
            int depth = word.getValue();

            for (int i = 0; i < wordCharArr.length; i++) {
                char oldChar = wordCharArr[i];
                for (char newChar = 'a'; newChar <= 'z'; newChar++) {
                    if (newChar == oldChar) continue;

                    wordCharArr[i] = newChar;
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
