package com.mxl2020.algorithms.practise.search.bfs;

import com.mxl2020.algorithms.practise.tree.disjointset.DisjointSet;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 岛屿数量
 *
 * @see <a href="https://leetcode-cn.com/problems/number-of-islands/">LeetCode 200</a>
 */
public class NumberOfIslands {

    private char[][] grid;

    /**
     * 广度优先搜索
     *
     * @param grid m * n 的栅格，其中 1 代表陆地，0 代表海洋
     * @return 返回岛屿的数量
     */
    public int numIslands(char[][] grid) {
        this.grid = grid;

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private void bfs(int x, int y) {
        grid[x][y] = '0';
        Queue<int[]> gridQueue = new ArrayDeque<>();
        gridQueue.offer(new int[]{x, y});

        while (!gridQueue.isEmpty()) {
            int[] land = gridQueue.poll();
            for (final int[] direction : directions) {
                int[] adjacentLand = {land[0] + direction[0], land[1] + direction[1]};
                if (isValidLand(adjacentLand[0], adjacentLand[1])) {
                    grid[adjacentLand[0]][adjacentLand[1]] = '0';
                    gridQueue.offer(adjacentLand);
                }
            }
        }
    }

    private boolean isValidLand(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1';
    }

    /**
     * 深度优先搜索
     */
    public int numIslands2(char[][] grid) {
        this.grid = grid;

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y) {
        // 递归终止条件
        if (!isValidLand(x, y)) return;
        grid[x][y] = '0';
        dfs(x + 1, y);
        dfs(x, y + 1);
        dfs(x - 1, y);
        dfs(x, y - 1);
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

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m * n + 1; i++) {
            set.add(disjointSet.find(i));
        }
        return set.size() - 1;
    }

}
