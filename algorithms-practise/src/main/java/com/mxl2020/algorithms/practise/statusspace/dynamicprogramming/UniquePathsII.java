package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 不同路径 II
 *
 * @see <a href="https://leetcode-cn.com/problems/unique-paths-ii/">LeetCode 63</a>
 */
public class UniquePathsII {

    /**
     * @param obstacleGrid 包含障碍物的网格。
     *                     机器人的初始位置为 obstacleGrid[0][0]；
     *                     值为 1 的格子是障碍物；
     *                     机器人只能向右或向下行走，且不能走到有障碍物的格子
     * @return 返回机器人可达到网格右下角的总的不同路径数
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        final int m = obstacleGrid.length;
        final int n = obstacleGrid[0].length;
        // 如果右下角是障碍物，直接返回 0
        if (obstacleGrid[m - 1][n - 1] == 1) return 0;
        // 定义状态：设 opt[i][j] 表示从 obstacleGrid[i][j] 走到右下角的路径数
        int[][] opt = new int[m + 1][n + 1];
        // 确定边界
        opt[m - 1][n - 1] = 1;
        // 从右下角开始遍历网格
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) continue;
                if (obstacleGrid[i][j] == 0) opt[i][j] = opt[i + 1][j] + opt[i][j + 1];
            }
        }
        return opt[0][0];
    }
}
