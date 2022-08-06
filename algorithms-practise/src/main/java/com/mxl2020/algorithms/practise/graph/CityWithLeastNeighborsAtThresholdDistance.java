package com.mxl2020.algorithms.practise.graph;

/**
 * 阈值距离内邻居最少的城市
 *
 * @see <a href="https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/">LeetCode 1334</a>
 */
public class CityWithLeastNeighborsAtThresholdDistance {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 建立带权图的邻接矩阵
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = (int) 1e9;
            }
        }
        for (int i = 0; i < n; i++) {
            d[i][i] = 0;
        }
        for (int[] edge : edges) {
            d[edge[0]][edge[1]] = d[edge[1]][edge[0]] = edge[2];
        }

        // Floyd 算法
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        // 求 distanceThreshold 内邻居最少的城市
        int city = -1;
        int minNumOfNeighbors = (int) 1e9;
        for (int i = 0; i < n; i++) {
            int numOfNeighbors = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && d[i][j] <= distanceThreshold) numOfNeighbors++;
            }
            if (numOfNeighbors <= minNumOfNeighbors) {
                minNumOfNeighbors = numOfNeighbors;
                city = i;
            }
        }
        return city;
    }
}
