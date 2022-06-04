package com.mxl2020.algorithms.practise.tree.trie;

/**
 * 实现 Trie（前缀树、字典树）
 *
 * @see <a href="https://leetcode.cn/problems/implement-trie-prefix-tree/">LeetCode 208</a>
 */
public class Trie {

    private final TrieNode root = new TrieNode();

    // 往 Trie 中插入一个单词
    public void insert(String word) {
        if (word.trim().equals("")) return;
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int charIndex = c - 'a';
            if (node.children[charIndex] == null) {
                node.children[charIndex] = new TrieNode();
            }
            node = node.children[charIndex];
        }
        node.isEndingChar = true;
    }

    // 在 Trie 中查找一个单词
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int charIndex = c - 'a';
            if (node.children[charIndex] == null) {
                return false;
            }
            node = node.children[charIndex];
        }
        return node.isEndingChar;
    }

    // 从 Trie 中寻找是否有匹配前缀 prefix 的单词
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int charIndex = c - 'a';
            if (node.children[charIndex] == null) {
                return false;
            }
            node = node.children[charIndex];
        }
        return true;
    }

    static class TrieNode {
        // public char data;
        // TODO 用 Map 代替数组实现一遍
        // TODO 用 count 代替 isEndingChar 实现一遍
        public int count;
        public final TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;
    }
}
