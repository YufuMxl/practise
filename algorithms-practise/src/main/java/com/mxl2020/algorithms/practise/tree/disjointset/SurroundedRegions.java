package com.mxl2020.algorithms.practise.tree.disjointset;

/**
 * 被围绕的区域
 *
 * @see <a href="https://leetcode.cn/problems/surrounded-regions/">LeetCode 130</a>
 */
public class SurroundedRegions {

    private int[] fa;

    // 只需要往下和往右移动
    private final int[][] directions = {{1, 0}, {0, 1}};

    public void makeSet(int length) {
        fa = new int[length];
        for (int i = 0; i < length; i++) {
            fa[i] = i;
        }
    }

    public void solve(char[][] board) {
        final int m = board.length;
        final int n = board[0].length;
        makeSet(m * n + 1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') continue;
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    unionSet(0, number(i, j, n));
                }
                for (int[] direction : directions) {
                    int subX = i + direction[0];
                    int subY = j + direction[1];
                    if (subX >= 0 && subX < m && subY >= 0 && subY < n && board[subX][subY] == 'O') {
                        unionSet(number(i, j, n), number(subX, subY, n));
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && find(number(i, j, n)) != find(0)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private int number(int x, int y, int n) {
        return x * n + y + 1;
    }

    private int find(int x) {
        if (fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }

    private void unionSet(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) fa[a] = b;
    }
}

// TODO dfs
