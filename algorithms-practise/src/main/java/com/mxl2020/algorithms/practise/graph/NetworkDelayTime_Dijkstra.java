package com.mxl2020.algorithms.practise.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkDelayTime_Dijkstra {

    public int networkDelayTime(int[][] times, int n, int k) {
        // 初始化带权出边数组
        List<ArrayList<int[]>> adjacencyArray = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adjacencyArray.add(i, new ArrayList<>());
        }
        for (int[] time : times) {
            adjacencyArray.get(time[0]).add(new int[]{time[1], time[2]});
        }

        // 初始化 dist 数组
        int[] dist = new int[n + 1];
        Arrays.fill(dist, (int) 1e9);
        dist[k] = 0;
        // 初始化 expand 数组
        boolean[] expand = new boolean[n + 1];
        expand[k] = true;

        int minDistVertex = k;
        while (minDistVertex != 0) {
            for (int[] edge : adjacencyArray.get(minDistVertex)) {
                if (dist[edge[0]] > dist[minDistVertex] + edge[1]) dist[edge[0]] = dist[minDistVertex] + edge[1];
            }
            minDistVertex = findMinDistVertexAndExpandIt(dist, expand);
        }

        int delayTime = dist[1];
        for (int i = 2; i <= n; i++) {
            delayTime = Math.max(delayTime, dist[i]);
        }
        return delayTime == (int) 1e9 ? -1 : delayTime;
    }

    private int findMinDistVertexAndExpandIt(int[] dist, boolean[] expand) {
        int minDistVertex = 0;
        for (int i = 1; i < dist.length; i++) {
            if (expand[i]) continue;
            if (dist[i] < dist[minDistVertex]) minDistVertex = i;
        }
        expand[minDistVertex] = true;
        return minDistVertex;
    }
}
