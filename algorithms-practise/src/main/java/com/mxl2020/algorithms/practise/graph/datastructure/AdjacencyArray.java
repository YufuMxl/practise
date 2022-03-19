package com.mxl2020.algorithms.practise.graph.datastructure;

import java.util.ArrayList;

/**
 * 出边数组
 * TODO 重命名 class
 */
public class AdjacencyArray implements Graph {

    private final ArrayList<Integer>[] graph;

    /**
     * @param n 图中元素的最大编号
     */
    public AdjacencyArray(int n) {
        this.graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    @Override
    public void addEdge(final int from, final int to) {
        graph[from].add(to);
    }
}
