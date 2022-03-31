package com.mxl2020.algorithms.practise.search.bfs;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 矩阵中的最长递增路径
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/">LeetCode 329</a>
 */
public class LongestIncreasingPathInMatrix {

    private final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private boolean isValidCell(int x, int y, int m, int n) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    /**
     * @param matrix 整数矩阵
     * @return 返回矩阵中的最长递增路径的长度
     */
    public int longestIncreasingPath(int[][] matrix) {
        final int m = matrix.length;
        final int n = matrix[0].length;

        int[][] inDegree = new int[m][n];
        Queue<Pair<int[], Integer>> bfsQueue = new ArrayDeque<>();

        // 初始化入度表；将入度为 0 的坐标入队
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] direction : directions) {
                    int x = i + direction[0];
                    int y = j + direction[1];
                    if (isValidCell(x, y, m, n) && matrix[x][y] < matrix[i][j]) inDegree[i][j]++;
                }
                if (inDegree[i][j] == 0) bfsQueue.offer(new Pair<>(new int[]{i, j}, 1));
            }
        }

        int longestPath = 0;
        while (!bfsQueue.isEmpty()) {
            Pair<int[], Integer> cellWithLevel = bfsQueue.poll();
            longestPath = Math.max(longestPath, cellWithLevel.getValue());

            int[] fatherCell = cellWithLevel.getKey();
            for (int[] direction : directions) {
                int x = fatherCell[0] + direction[0];
                int y = fatherCell[1] + direction[1];
                if (isValidCell(x, y, m, n) && matrix[x][y] > matrix[fatherCell[0]][fatherCell[1]]) {
                    inDegree[x][y]--;
                    if (inDegree[x][y] == 0) bfsQueue.offer(new Pair<>(new int[]{x, y}, cellWithLevel.getValue() + 1));
                }
            }
        }
        return longestPath;
    }
}
