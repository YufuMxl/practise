package com.mxl2020.algorithms.practise.search.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

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

}
