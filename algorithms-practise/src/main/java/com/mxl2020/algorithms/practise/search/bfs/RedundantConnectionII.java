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
    private List<Set<Integer>> adjacencyArray;

    /**
     * @param edges 有根基环树（有向图）的边。节点数为 n，边数为 n；节点的编号从 1 到 n
     * @return 找到形成环的那条边并返回。如果答案有多个，返回 edges 中最后出现的那条边
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        n = edges.length;
        inDegree = new int[n + 1];
        adjacencyArray = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacencyArray.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            adjacencyArray.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        int root = 0;
        int maxInDegreeNode = 0;
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) root = i;
            if (inDegree[i] == 2) maxInDegreeNode = i;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] edge = edges[i];
            if (root != 0 && maxInDegreeNode != 0) {
                // 1.存在一个入度为 2 的节点和一个入度为 0 的根节点
                if (edge[1] == maxInDegreeNode && hasNoCycle(root, edge)) return edge;
            } else {
                // 2.所有节点的入度都为 1
                if (hasNoCycle(edge[1], edge)) return edge;
            }
        }

        return null;
    }

    /**
     * 广度优先遍历：完整遍历一棵树 == 无环
     *
     * @param removedEdge 假设被移除的一条边
     */
    private boolean hasNoCycle(int root, int[] removedEdge) {
        int[] inDegreeCopy = Arrays.copyOf(inDegree, inDegree.length);
        // 删边
        adjacencyArray.get(removedEdge[0]).remove(removedEdge[1]);
        inDegreeCopy[removedEdge[1]]--;

        int visitedCount = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visitedCount++;
            for (int child : adjacencyArray.get(node)) {
                if (--inDegreeCopy[child] == 0) queue.offer(child);
            }
        }

        // 还原
        adjacencyArray.get(removedEdge[0]).add(removedEdge[1]);

        return visitedCount == n;
    }

    // TODO 解法 2：并查集
}
