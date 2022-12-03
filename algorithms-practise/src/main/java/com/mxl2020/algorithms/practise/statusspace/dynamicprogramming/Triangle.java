package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * 三角形最小路径和
 *
 * @see <a href="https://leetcode.cn/problems/triangle/">LeetCode 120</a>
 */
public class Triangle {

    /**
     * @param triangle 给定一个三角形
     * @return 找出自顶向下的最小路径和
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] opt = new int[triangle.size()];
        Arrays.fill(opt, (int) 1e9);
        opt[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = i; j >= 1; j--) {
                opt[j] = Math.min(opt[j - 1], opt[j]) + triangle.get(i).get(j);
            }
            opt[0] += triangle.get(i).get(0);
        }

        int minPath = (int) 1e9;
        for (int path : opt) {
            minPath = Math.min(path, minPath);
        }

        return minPath;
    }
}
