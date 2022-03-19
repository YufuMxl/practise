package com.mxl2020.algorithms.practise.graph.datastructure;

/**
 * 邻接矩阵
 */
public class AdjacencyMatrix implements Graph {

    private final int[][] graph;

    /**
     * @param n 图中元素的最大编号
     */
    public AdjacencyMatrix(int n) {
        this.graph = new int[n][n];
    }

    @Override
    public void addEdge(final int from, final int to) {
        graph[from][to] = 1;
    }
}
