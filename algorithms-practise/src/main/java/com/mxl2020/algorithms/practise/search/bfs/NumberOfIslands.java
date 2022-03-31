package com.mxl2020.algorithms.practise.search.bfs;

/**
 * 岛屿数量
 *
 * @see <a href="https://leetcode-cn.com/problems/number-of-islands/">LeetCode 200</a>
 */
public class NumberOfIslands {

    /**
     * 深度优先搜索
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
                    dfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private char[][] grid;

    private void dfs(int x, int y) {
        // 递归终止条件
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') return;
        grid[x][y] = '0';
        dfs(x + 1, y);
        dfs(x, y + 1);
        dfs(x - 1, y);
        dfs(x, y - 1);
    }

}


