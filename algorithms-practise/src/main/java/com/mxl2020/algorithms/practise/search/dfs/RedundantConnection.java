package com.mxl2020.algorithms.practise.search.dfs;

import com.mxl2020.algorithms.practise.tree.disjointset.DisjointSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 冗余连接
 *
 * @see <a href="https://leetcode-cn.com/problems/redundant-connection/">LeetCode 684</a>
 */
public class RedundantConnection {
    /**
     * 深度优先遍历
     * <p>
     * 树的定义：n 个节点，n - 1 条边的"无环、无向、连通图"
     * <p>
     * 基环树的定义：给树多加一条边
     *
     * @param edges 基环树的边，边数为 n
     * @return 找到形成基环树的那条边并返回，如果答案有多个，返回 edges 中最后出现的那条边
     */
    public int[] findRedundantConnection(int[][] edges) {
        final int n = edges.length;
        visited = new boolean[n + 1];
        adjacencyArray = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacencyArray.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyArray.get(edge[0]).add(edge[1]);
            adjacencyArray.get(edge[1]).add(edge[0]);
            dfs(edge[0], 0);
            if (hasCycle) return edge;
            else visited = new boolean[n + 1];
        }
        return null;
    }

    private List<List<Integer>> adjacencyArray;
    private boolean[] visited;
    private boolean hasCycle;

    // 无向图判断环的模板
    private void dfs(int node, int father) {
        visited[node] = true;
        for (int child : adjacencyArray.get(node)) {
            if (child == father) continue;
            if (!visited[child]) dfs(child, node);
            else hasCycle = true;
        }
    }

    /**
     * 并查集解法
     */
    public int[] findRedundantConnection2(int[][] edges) {
        DisjointSet disjointSet = new DisjointSet(edges.length + 1);
        for (int[] edge : edges) {
            if (!disjointSet.tryUnionSet(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[2];
    }

}
