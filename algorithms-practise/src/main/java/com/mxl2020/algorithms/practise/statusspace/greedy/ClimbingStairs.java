package com.mxl2020.algorithms.practise.statusspace.greedy;

/**
 * 爬楼梯
 *
 * @see <a href="https://leetcode.cn/problems/climbing-stairs/">LeetCode 70</a>
 */
public class ClimbingStairs {

    /**
     * @param n n 个台阶；每次爬 1 或 2 个台阶
     * @return 有多少种不同的爬到楼顶的方法
     */
    public int climbStairs(int n) {
        int[] opt = new int[n + 1];
        opt[0] = 1;
        opt[1] = 1;
        for (int i = 2; i <= n; i++) {
            opt[i] = opt[i - 1] + opt[i - 2];
        }

        return opt[n];
    }

    public int climbStairs2(int n) {
        int[] opt = new int[2];
        opt[0] = 1;
        opt[1] = 1;
        for (int i = 2; i <= n; i++) {
            opt[i % 2] = opt[(i - 1) % 2] + opt[i % 2];
        }
        return opt[n % 2];
    }
}
