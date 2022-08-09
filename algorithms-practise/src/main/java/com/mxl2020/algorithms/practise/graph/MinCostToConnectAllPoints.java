package com.mxl2020.algorithms.practise.graph;

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
                int x = i;
                int y = j;
                int z = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{x, y, z});
            }
        }
        // 按照边的权值，对边集进行排序
        edges.sort(Comparator.comparingInt(edge -> edge[2]));

        // 利用并查集建立最小生成树
        DisjointSet mst = new DisjointSet(n);
        int cost = 0;
        for (int[] edge : edges) {
            if (mst.unionSet(edge[0], edge[1])) cost += edge[2];
        }

        return cost;
    }

    static class DisjointSet {
        private final int[] father;

        public DisjointSet(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }

        public int find(int x) {
            if (father[x] == x) return x;
            return father[x] = find(father[x]);
        }

        public boolean unionSet(int a, int b) {
            a = find(a);
            b = find(b);
            if (a != b) {
                father[a] = b;
                return true;
            }
            return false;
        }
    }
}
