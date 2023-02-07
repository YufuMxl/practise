package com.mxl2020.algorithms.practise.tree.disjointset;

/**
 * 被围绕的区域
 *
 * @see <a href="https://leetcode.cn/problems/surrounded-regions/">LeetCode 130</a>
 */
public class SurroundedRegions {

    /**
     * 解法 1：并查集
     *
     * @param board m * n 的矩阵
     */
    public void solve(char[][] board) {
        this.m = board.length;
        this.n = board[0].length;
        final int[][] directions = {{1, 0}, {0, 1}};
        DisjointSet disjointSet = new DisjointSet(m * n + 1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') continue;
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    disjointSet.unionSet(0, number(i, j));
                }
                for (int[] direction : directions) {
                    int subX = i + direction[0];
                    int subY = j + direction[1];
                    if (subX >= 0 && subX < m && subY >= 0 && subY < n && board[subX][subY] == 'O') {
                        disjointSet.unionSet(number(i, j), number(subX, subY));
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && disjointSet.find(number(i, j)) != disjointSet.find(0)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private int number(int x, int y) {
        return x * n + y + 1;
    }

    /**
     * 解法 2：dfs
     */
    public void solve2(char[][] board) {
        this.m = board.length;
        this.n = board[0].length;
        this.board = board;
        this.visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(i, 0);
            if (board[i][n - 1] == 'O') dfs(i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') dfs(0, j);
            if (board[m - 1][j] == 'O') dfs(m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) board[i][j] = 'X';
            }
        }
    }

    private int m;
    private int n;
    private char[][] board;
    private boolean[][] visited;
    private final byte[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void dfs(final int x, final int y) {
        visited[x][y] = true;
        for (byte[] direction : directions) {
            int subX = x + direction[0];
            int subY = y + direction[1];
            if (isValid(subX, subY) && board[subX][subY] == 'O' && !visited[subX][subY]) dfs(subX, subY);
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}

