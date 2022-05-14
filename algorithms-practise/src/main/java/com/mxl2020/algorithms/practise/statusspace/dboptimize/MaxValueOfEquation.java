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

    /**
     * 从"暴力破解"开始优化，优化到最后就是前缀 max
     * <p>
     * 给定 n 个二元组 (x1,y1),(x2,y2)...(xn,yn)，已经按照 x 从小到大排序
     * <p>
     * 求 yi + yj + |xi - xj| 的最大值，其中 i != j
     */
    public int findMaxValueOfEquationDemo(int[][] points, int k) {
        int maxValue = (int) -1e9;
        int temp = (int) -1e9;
        for (int i = 1; i < points.length; i++) {
            temp = Math.max(temp, points[i - 1][1] - points[i - 1][0]);
            maxValue = Math.max(maxValue, points[i][1] + points[i][0] + temp);
        }
        return maxValue;
    }
}
