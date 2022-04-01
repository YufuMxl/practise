package com.mxl2020.algorithms.practise.search.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 矩阵中的最长递增路径
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/">LeetCode 329</a>
 */
public class LongestIncreasingPathInMatrix {

    /**
     * @param matrix m * n 的整数矩阵
     * @return 返回矩阵中的最长递增路径的长度
     */
    public int longestIncreasingPath(int[][] matrix) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        final byte[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        int[][] inDegree = new int[m][n];
        Queue<int[]> bfsQueue = new ArrayDeque<>();

        // 初始化入度表；将入度为 0 的坐标入队
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (byte[] direction : directions) {
                    int x = i + direction[0];
                    int y = j + direction[1];
                    if (isValidCell(x, y, m, n) && matrix[x][y] < matrix[i][j]) inDegree[i][j]++;
                }
                if (inDegree[i][j] == 0) bfsQueue.offer(new int[]{i, j, 1});
            }
        }

        int longestPath = 0;
        while (!bfsQueue.isEmpty()) {
            int[] parentCell = bfsQueue.poll();
            longestPath = Math.max(longestPath, parentCell[2]);
            for (byte[] direction : directions) {
                int x = parentCell[0] + direction[0];
                int y = parentCell[1] + direction[1];
                if (isValidCell(x, y, m, n) && matrix[x][y] > matrix[parentCell[0]][parentCell[1]]) {
                    if (--inDegree[x][y] == 0) bfsQueue.offer(new int[]{x, y, parentCell[2] + 1});
                }
            }
        }
        return longestPath;
    }

    private boolean isValidCell(int x, int y, int m, int n) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    /**
     * DFS 解法
     */
    public int longestIncreasingPath2(int[][] matrix) {
        this.matrix = matrix;
        this.pathStore = new int[matrix.length][matrix[0].length];

        int longestPath = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                longestPath = Math.max(longestPath, dfs(i, j));
            }
        }

        return longestPath;
    }

    private int[][] matrix;
    private int[][] pathStore;
    private final byte[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * 求一个点的最远路径
     */
    private int dfs(int x, int y) {
        if (pathStore[x][y] != 0) return pathStore[x][y];

        int maxSubPath = 0;
        for (byte[] direction : directions) {
            int subX = x + direction[0];
            int subY = y + direction[1];
            if (isValidCell(subX, subY, matrix.length, matrix[0].length) && matrix[subX][subY] > matrix[x][y]) {
                maxSubPath = Math.max(maxSubPath, dfs(subX, subY));
            }
        }

        pathStore[x][y] = 1 + maxSubPath;
        return pathStore[x][y];
    }
}
