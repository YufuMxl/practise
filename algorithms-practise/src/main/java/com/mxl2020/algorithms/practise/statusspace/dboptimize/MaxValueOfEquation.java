package com.mxl2020.algorithms.practise.statusspace.dboptimize;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 满足不等式的最大值
 *
 * @see <a href="https://leetcode.cn/problems/max-value-of-equation/">LeetCode 1499</a>
 */
public class MaxValueOfEquation {

    /**
     * @param points 保存坐标的二维数组，points[i] = [xi, yi]，points 以 x 排序
     * @param k      限制值
     * @return 返回 yi + yj + |xi - xj| 的最大值，要满足 |xi - xj| <= k
     */
    public int findMaxValueOfEquation(int[][] points, int k) {
        int maxValue = (int) -1e9;
        Deque<Integer> sw = new ArrayDeque<>();
        for (int i = 1; i < points.length; i++) {
            int j = i - 1;
            if (points[i][0] - points[j][0] > k) continue;
            // 移除范围外的数据
            while (!sw.isEmpty() && points[i][0] - points[sw.peekFirst()][0] > k) {
                sw.pollFirst();
            }
            // 维护队列单调性
            while (!sw.isEmpty() && points[sw.peekLast()][1] - points[sw.peekLast()][0] < points[j][1] - points[j][0]) {
                sw.pollLast();
            }
            // 入队
            sw.offerLast(j);
            maxValue = Math.max(maxValue, points[i][1] + points[i][0] + points[sw.peekFirst()][1] - points[sw.peekFirst()][0]);
        }
        return maxValue;
    }
}
