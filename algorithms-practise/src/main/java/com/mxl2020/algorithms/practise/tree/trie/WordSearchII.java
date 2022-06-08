package com.mxl2020.algorithms.practise.tree.trie;

import java.util.*;

/**
 * 单词搜索 II
 *
 * @see <a href="https://leetcode.cn/problems/word-search-ii/">LeetCode 212</a>
 */
public class WordSearchII {
    private List<String> ans;
    private char[][] board;
    private int m;
    private int n;
    private boolean[][] visited;
    private StringBuilder wordBuilder;
    private TrieNode root;

    private final int[][] directions = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        this.ans = new ArrayList<>();
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        this.visited = new boolean[m][n];
        this.wordBuilder = new StringBuilder();

        // 生成 Trie 树
        this.root = new TrieNode();
        for (String word : words) {
            insert(word);
        }

        // 深度优先遍历 board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, this.root);
            }
        }

        return ans;
    }

    private void dfs(final int x, final int y, final TrieNode node) {
        int currentIndex = board[x][y] - 'a';
        TrieNode currentNode = node.children[currentIndex];
        if (currentNode == null) return;

        wordBuilder.append(board[x][y]);
        if (currentNode.wordTag) {
            ans.add(wordBuilder.toString());
            currentNode.wordTag = false;
            if (currentNode.isEmpty()) {
                node.children[currentIndex] = null;
                wordBuilder.deleteCharAt(wordBuilder.length() - 1);
                return;
            }
        }
        visited[x][y] = true;
        for (int[] direction : directions) {
            int subX = x + direction[0];
            int subY = y + direction[1];
            if (subX < 0 || subX == m || subY < 0 || subY == n || visited[subX][subY]) continue;
            dfs(subX, subY, currentNode);
        }
        visited[x][y] = false;
        wordBuilder.deleteCharAt(wordBuilder.length() - 1);
    }

    private void insert(String word) {
        TrieNode node = this.root;
        for (char c : word.toCharArray()) {
            int cIndex = c - 'a';
            if (node.children[cIndex] == null) {
                node.children[cIndex] = new TrieNode();
            }
            node = node.children[cIndex];
        }
        node.wordTag = true;
    }

    static class TrieNode {
        public boolean wordTag;
        public final TrieNode[] children = new TrieNode[26];

        public boolean isEmpty() {
            for (TrieNode node : this.children) {
                if (node != null) return false;
            }
            return true;
        }
    }
}
