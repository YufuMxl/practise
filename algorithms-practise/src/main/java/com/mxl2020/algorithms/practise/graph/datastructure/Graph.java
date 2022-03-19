package com.mxl2020.algorithms.practise.graph.datastructure;

public interface Graph {

    /**
     * 新增一条有向边
     *
     * @param from 首节点
     * @param to   尾节点
     */
    void addEdge(int from, int to);
}
