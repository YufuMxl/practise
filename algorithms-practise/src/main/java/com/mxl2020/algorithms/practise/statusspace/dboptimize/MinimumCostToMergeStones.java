package com.mxl2020.algorithms.practise.statusspace.dboptimize;

/**
 * 合并石头的最低成本
 *
 * @see <a href="https://leetcode.cn/problems/minimum-cost-to-merge-stones/">LeetCode 1000</a>
 */
public class MinimumCostToMergeStones {

    /**
     * @param stones n 堆石头
     * @param k      以 k 堆为一组合并石头
     * @return 返回 stones 被合成一堆后的最小合并代价
     */
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        // 数组长度 n 要满足 (n - 1) % (k - 1) == 0
        if ((n - 1) % (k - 1) != 0) return -1;

        // 本题的变量：stones 数组、最低成本
        // opt[l][r] 记录 l r 区间内合并石头的最低成本
        int[][] opt = new int[n][n];

        // 从最小区间开始计算 opt
        for (int len = k; len <= n; len += (k - 1)) {
            for (int l = 0; l <= n - len; l++) {
                int r = l + len - 1;
                // TODO
            }
        }

        return opt[0][n - 1];
    }
}
