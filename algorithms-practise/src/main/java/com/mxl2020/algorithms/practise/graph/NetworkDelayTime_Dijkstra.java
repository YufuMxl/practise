package com.mxl2020.algorithms.practise.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class NetworkDelayTime_Dijkstra {

    public int networkDelayTime(int[][] times, int n, int k) {
        // 初始化 dist 数组、expanded 数组
        int[] dist = new int[n + 1];
        boolean[] expanded = new boolean[n + 1];
        Arrays.fill(dist, (int) 1e9);
        dist[k] = 0;

        // 初始化带权出边数组
        ArrayList<ArrayList<int[]>> adjacencyArray = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adjacencyArray.add(i, new ArrayList<>());
        }
        for (int[] time : times) {
            adjacencyArray.get(time[0]).add(new int[]{time[1], time[2]});
        }

        while (true) {
            int minDistVertex = findMinDistVertexAndExpandIt(dist, expanded);
            if (minDistVertex == -1) break;

            for (int[] edge : adjacencyArray.get(minDistVertex)) {
                if (dist[edge[0]] > dist[minDistVertex] + edge[1]) dist[edge[0]] = dist[minDistVertex] + edge[1];
            }
        }

        int delayTime = dist[1];
        for (int i = 2; i <= n; i++) {
            delayTime = Math.max(delayTime, dist[i]);
        }
        return delayTime == (int) 1e9 ? -1 : delayTime;
    }

    private int findMinDistVertexAndExpandIt(int[] dist, boolean[] expanded) {
        int minDistVertex = -1;
        for (int i = 1; i < dist.length; i++) {
            if (expanded[i]) continue;
            if (minDistVertex == -1) minDistVertex = i;
            if (dist[i] < dist[minDistVertex]) minDistVertex = i;
        }
        if (minDistVertex != -1) expanded[minDistVertex] = true;
        return minDistVertex;
    }
}
