package com.mxl2020.algorithms.practise.search.bfs;

import java.util.*;

/**
 * 冗余连接 II
 *
 * @see <a href="https://leetcode-cn.com/problems/redundant-connection-ii/">LeetCode 685</a>
 */
public class RedundantConnectionII {

    private int n;
    private int[] inDegree;
    private ArrayList<HashSet<Integer>> graph;

    /**
     * @param edges 有根基环树（有向图）的边。节点数为 n，边数为 n；节点的编号从 1 到 n
     * @return 找到形成环的那条边并返回。如果答案有多个，返回 edges 中最后出现的那条边
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        this.n = edges.length;
        // 初始化"入度表"
        this.inDegree = new int[this.n + 1];
        // 初始化"出边数组"
        this.graph = new ArrayList<>(this.n + 1);
        for (int i = 0; i <= this.n; i++) {
            this.graph.add(i, new HashSet<>());
        }
        // 存储"有向树"
        for (int[] edge : edges) {
            this.graph.get(edge[0]).add(edge[1]);
            this.inDegree[edge[1]]++;
        }

        int root = -1;
        int maxInDegreeNode = -1;
        for (int i = 1; i < this.inDegree.length; i++) {
            if (this.inDegree[i] == 0) root = i;
            if (this.inDegree[i] == 2) maxInDegreeNode = i;
        }

        // 有根基环树的入度总和为 n，存在两种情况
        if (maxInDegreeNode != -1 && root != -1) {
            // 1.存在一个入度为 2 的节点和一个入度为 0 的根节点
            for (int i = this.n - 1; i >= 0; i--) {
                if (edges[i][1] == maxInDegreeNode && hasNoCycle(root, edges[i])) return edges[i];
            }
        } else {
            // 2.所有节点的入度都为 1
            for (int i = this.n - 1; i >= 0; i--) {
                // 去掉某边之后，判断是否还存在环
                if (hasNoCycle(edges[i][1], edges[i])) return edges[i];
            }
        }

        return null;
    }

    /**
     * 广度优先遍历：完整遍历一棵树 == 无环
     *
     * @param root        根节点
     * @param removedEdge 假设被移除的一条边
     * @return 是否无环
     */
    private boolean hasNoCycle(int root, int[] removedEdge) {
        int[] inDegreeCopy = Arrays.copyOf(this.inDegree, this.inDegree.length);
        // 从图中删除一条边
        this.graph.get(removedEdge[0]).remove(removedEdge[1]);
        inDegreeCopy[removedEdge[1]]--;

        int visitedCount = 0;
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(root);
        while (!bfsQueue.isEmpty()) {
            int node = bfsQueue.poll();
            visitedCount++;
            for (int child : graph.get(node)) {
                if (--inDegreeCopy[child] == 0) bfsQueue.offer(child);
            }
        }

        // 还原图
        graph.get(removedEdge[0]).add(removedEdge[1]);
        return visitedCount == this.n;
    }

    // TODO 解法 2：深度优先遍历找有向图的环（尚未访问 0、正在访问 1、已完毕 2）
    // TODO 解法 3：该题还可以用并查集，但是等学到了再实现吧
}
