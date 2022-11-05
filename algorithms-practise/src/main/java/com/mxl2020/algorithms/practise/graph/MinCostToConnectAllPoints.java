package com.mxl2020.algorithms.practise.graph;

import com.mxl2020.algorithms.practise.tree.disjointset.DisjointSet;

import java.util.*;

/**
 * 连接所有点的最小费用
 *
 * @see <a href="https://leetcode.cn/problems/min-cost-to-connect-all-points/">LeetCode 1584</a>
 */
public class MinCostToConnectAllPoints {

    /**
     * @param points 表示 2D 平面上的一些点：points[i] = [xi, yi]。
     *               两点之间的曼哈顿距离就是两点之间的费用：|xi - xj| + |yi - yj|
     * @return 返回将所有点连接的最小总费用
     */
    public int minCostConnectPoints(int[][] points) {
        final int n = points.length;
        // 将点集转换成边集
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});
            }
        }
        // 按照边的权值，对边集进行排序
        edges.sort(Comparator.comparingInt(edge -> edge[2]));

        // 利用并查集建立最小生成树
        DisjointSet mst = new DisjointSet(n);
        int cost = 0;
        for (int[] edge : edges) {
            if (mst.tryUnionSet(edge[0], edge[1])) cost += edge[2];
        }

        return cost;
    }
}
