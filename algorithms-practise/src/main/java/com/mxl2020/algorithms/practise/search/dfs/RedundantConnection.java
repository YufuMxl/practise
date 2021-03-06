package com.mxl2020.algorithms.practise.search.dfs;

import java.util.ArrayList;
import java.util.Arrays;

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
        // 初始化"遍历记录表"
        visited = new boolean[edges.length + 1];
        // 初始化"出边数组"
        graph = new ArrayList<>(edges.length + 1);
        for (int i = 0; i <= edges.length; i++) {
            graph.add(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            dfs(edge[0], 0);
            if (hasCycle) return edge;
            else Arrays.fill(visited, false);
        }
        return null;
    }

    private ArrayList<ArrayList<Integer>> graph;
    private boolean[] visited;
    private boolean hasCycle;

    // 无向图判断环的模板
    private void dfs(int node, int father) {
        visited[node] = true;
        for (int child : graph.get(node)) {
            // 递归终止条件：遇到父节点
            if (child == father) continue;
            // 递归终止条件：遇到曾遍历过的节点（此处成环）
            if (visited[child]) hasCycle = true;
            else dfs(child, node);
        }
    }

}
