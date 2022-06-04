package com.mxl2020.algorithms.practise.tree.trie;

import java.util.*;

/**
 * 单词搜索 II
 *
 * @see <a href="https://leetcode.cn/problems/word-search-ii/">LeetCode 212</a>
 */
public class WordSearchII {
    private Set<String> ans;
    private char[][] board;
    private int m;
    private int n;
    private boolean[][] visited;
    private StringBuilder wordBuilder;

    private final int[][] directions = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        this.ans = new HashSet<>();
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        this.visited = new boolean[m][n];
        this.wordBuilder = new StringBuilder();

        // 生生 Trie 树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        // 深度优先遍历 board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, trie.root);
            }
        }

        return new ArrayList<>(ans);
    }

    private void dfs(final int x, final int y, final TrieNode node) {
        TrieNode currentNode = node.children.get(board[x][y]);
        if (currentNode == null) return;

        wordBuilder.append(board[x][y]);
        if (currentNode.count > 0) {
            currentNode.count = 0;
            ans.add(wordBuilder.toString());
        }
        visited[x][y] = true;
        for (int[] direction : directions) {
            int subX = x + direction[0];
            int subY = y + direction[1];
            if (subX < 0 || subX == m || subY < 0 || subY == n) continue;
            if (visited[subX][subY]) continue;
            dfs(subX, subY, currentNode);
        }
        visited[x][y] = false;
        wordBuilder.deleteCharAt(wordBuilder.length() - 1);
    }

    static class Trie {
        private final TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children.get(c) == null) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.count += 1;
        }

        public boolean search(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children.get(c) == null) {
                    return false;
                }
                node = node.children.get(c);
            }
            return node.count > 0;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                if (node.children.get(c) == null) {
                    return false;
                }
                node = node.children.get(c);
            }
            return true;
        }
    }

    static class TrieNode {
        public int count;
        public final Map<Character, TrieNode> children;

        public TrieNode() {
            this.count = 0;
            this.children = new HashMap<>();
        }
    }
}
