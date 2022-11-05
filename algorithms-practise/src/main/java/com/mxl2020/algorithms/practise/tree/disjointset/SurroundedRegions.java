package com.mxl2020.algorithms.practise.tree.disjointset;

/**
 * 被围绕的区域
 *
 * @see <a href="https://leetcode.cn/problems/surrounded-regions/">LeetCode 130</a>
 */
public class SurroundedRegions {

    // 只需要往下和往右移动
    private final int[][] directions = {{1, 0}, {0, 1}};

    /**
     * 解法 1：并查集
     *
     * @param board m * n 的矩阵
     */
    public void solve(char[][] board) {
        final int m = board.length;
        final int n = board[0].length;
        DisjointSet disjointSet = new DisjointSet(m * n + 1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') continue;
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    disjointSet.unionSet(0, number(i, j, n));
                }
                for (int[] direction : directions) {
                    int subX = i + direction[0];
                    int subY = j + direction[1];
                    if (subX >= 0 && subX < m && subY >= 0 && subY < n && board[subX][subY] == 'O') {
                        disjointSet.unionSet(number(i, j, n), number(subX, subY, n));
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && disjointSet.find(number(i, j, n)) != disjointSet.find(0)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private int number(int x, int y, int n) {
        return x * n + y + 1;
    }

    /**
     * 解法 2：dfs
     *
     * @param board m * n 的矩阵
     */
    public void solve2(char[][] board) {
        final int m = board.length;
        final int n = board[0].length;
        this.board = board;
        this.visited = new boolean[m][n];

        final int m_1 = m - 1;
        final int n_1 = n - 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m_1 || j == n_1) {
                    if (board[i][j] == 'O' && !visited[i][j]) {
                        visited[i][j] = true;
                        dfs(i, j);
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) board[i][j] = 'X';
            }
        }
    }

    private char[][] board;
    private boolean[][] visited;
    private final int[][] fourDirections = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    private void dfs(final int x, final int y) {
        for (int[] direction : fourDirections) {
            int subX = x + direction[0];
            int subY = y + direction[1];
            if (subX < 0 || subX >= board.length || subY < 0 || subY >= board[0].length) continue;
            if (board[subX][subY] == 'X') continue;
            if (visited[subX][subY]) continue;
            visited[subX][subY] = true;
            dfs(subX, subY);
        }
    }
}

