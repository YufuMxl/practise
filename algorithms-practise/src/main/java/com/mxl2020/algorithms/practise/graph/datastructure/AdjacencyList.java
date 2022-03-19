package com.mxl2020.algorithms.practise.graph.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 邻接表
 */
public class AdjacencyList implements Graph {

    private final ArrayList<LinkedList<Integer>> graph;

    /**
     * @param n 图中元素的最大编号
     */
    public AdjacencyList(int n) {
        this.graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(i, new LinkedList<>());
        }
    }

    @Override
    public void addEdge(final int from, final int to) {
        graph.get(from).addFirst(to);
    }
}
