package com.mxl2020.algorithms.practise.search.bfs;

import com.mxl2020.algorithms.practise.tree.disjointset.DisjointSet;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 岛屿数量
 *
 * @see <a href="https://leetcode-cn.com/problems/number-of-islands/">LeetCode 200</a>
 */
public class NumberOfIslands {

    private int m;
    private int n;
    private char[][] grid;
    private boolean[][] visited;
    private final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * 广度优先搜索
     *
     * @param grid m * n 的栅格，其中 1 代表陆地，0 代表海洋
     * @return 返回岛屿的数量
     */
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.visited = new boolean[m][n];
        this.grid = grid;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(int x, int y) {
        visited[x][y] = true;
        Queue<int[]> gridQueue = new ArrayDeque<>();
        gridQueue.offer(new int[]{x, y});

        while (!gridQueue.isEmpty()) {
            int[] land = gridQueue.poll();
            for (final int[] direction : directions) {
                int subX = land[0] + direction[0];
                int subY = land[1] + direction[1];
                if (isValidLand(subX, subY)) {
                    visited[subX][subY] = true;
                    gridQueue.offer(new int[]{subX, subY});
                }
            }
        }
    }

    private boolean isValidLand(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == '1';
    }

    /**
     * 深度优先搜索
     */
    public int numIslands2(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.visited = new boolean[m][n];
        this.grid = grid;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y) {
        // 递归终止条件
        if (x < 0 || x >= m || y < 0 || y >= n) return;
        if (grid[x][y] == '0') return;
        if (visited[x][y]) return;
        visited[x][y] = true;
        dfs(x - 1, y);
        dfs(x + 1, y);
        dfs(x, y - 1);
        dfs(x, y + 1);
    }

    /**
     * 并查集
     */
    public int numIslands3(char[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        final DisjointSet disjointSet = new DisjointSet(m * n + 1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = i * n + j + 1;
                if (grid[i][j] == '0') disjointSet.unionSet(num, 0);
                else {
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') disjointSet.unionSet(num, num - n);
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') disjointSet.unionSet(num, num - 1);
                }
            }
        }

        return disjointSet.getSetCount() - 1;
    }

}
