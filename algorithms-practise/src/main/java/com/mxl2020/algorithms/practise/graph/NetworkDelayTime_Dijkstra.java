package com.mxl2020.algorithms.practise.graph;

import java.util.*;

/**
 * 网络延迟时间
 *
 * @see <a href="https://leetcode.cn/problems/network-delay-time/">LeetCode 743</a>
 */
public class NetworkDelayTime_Dijkstra {

    public int networkDelayTime(int[][] times, int n, int k) {
        // 初始化带权出边数组
        ArrayList<int[]>[] adjacencyArray = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjacencyArray[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            adjacencyArray[time[0]].add(new int[]{time[1], time[2]});
        }

        // 初始化 dist 数组
        int[] dist = new int[n + 1];
        Arrays.fill(dist, (int) 1e9);
        dist[k] = 0;
        // 初始化 expand 数组
        boolean[] expand = new boolean[n + 1];
        // 初始化优先队列
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        q.offer(new int[]{k, 0});

        while (!q.isEmpty()) {
            int[] top = q.poll();
            int minDistVertex = top[0];
            if (expand[minDistVertex]) continue;
            expand[minDistVertex] = true;
            for (int[] edge : adjacencyArray[minDistVertex]) {
                if (dist[edge[0]] > dist[minDistVertex] + edge[1]) {
                    dist[edge[0]] = dist[minDistVertex] + edge[1];
                    q.offer(new int[]{edge[0], dist[edge[0]]});
                }
            }
        }

        int delayTime = dist[1];
        for (int i = 2; i <= n; i++) {
            delayTime = Math.max(delayTime, dist[i]);
        }
        return delayTime == (int) 1e9 ? -1 : delayTime;
    }
}
