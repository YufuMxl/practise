package com.mxl2020.algorithms.practise.statusspace.dboptimize;

/**
 * 满足不等式的最大值
 *
 * @see <a href="https://leetcode.cn/problems/max-value-of-equation/">LeetCode 1499</a>
 */
public class MaxValueOfEquation {

    public int findMaxValueOfEquation(int[][] points, int k) {
        return -1;
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
