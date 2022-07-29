package com.mxl2020.algorithms.practise.graph;

import java.util.Arrays;

/**
 * 网络延迟时间
 *
 * @see <a href="https://leetcode.cn/problems/network-delay-time/">LeetCode 743</a>
 */
public class NetworkDelayTime {

    /**
     * @param times times[i] = (ui, vi, wi)，表示一条有向边，其中 wi 表示信号从 ui 到 vi 的延迟时间
     * @param n     n 个顶点，编号为 1 到 n
     * @param k     k 为起点
     * @return 从 k 发出一个信号，返回所有顶点都接收到信号的最短延迟；如果 n 个节点无法全部接收到信号，返回 -1
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, (int) 1e9);
        dist[k] = 0;

        // 循环 n - 1 次
        for (int i = 0; i < n - 1; i++) {
            boolean changed = false;
            for (int[] time : times) {
                if (dist[time[1]] > dist[time[0]] + time[2]) {
                    dist[time[1]] = dist[time[0]] + time[2];
                    changed = true;
                }
            }
            if (!changed) break;
        }

        int delayTime = dist[1];
        for (int i = 2; i <= n; i++) {
            delayTime = Math.max(delayTime, dist[i]);
        }
        if (delayTime == 1e9) delayTime = -1;
        return delayTime;
    }
}
