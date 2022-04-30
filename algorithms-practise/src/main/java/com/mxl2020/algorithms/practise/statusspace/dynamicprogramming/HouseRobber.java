package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 打家劫舍
 *
 * @see <a href="https://leetcode-cn.com/problems/house-robber/">LeetCode 198</a>
 */
public class HouseRobber {

    /**
     * @param nums 非负整数数组，代表每个房子藏匿的现金
     * @return 返回能偷盗的最大现金数量（不能连续偷相邻的两家）
     */
    public int rob(int[] nums) {
        int[][] opt = new int[2][2];
        opt[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            opt[i & 1][0] = Math.max(opt[i - 1 & 1][0], opt[i - 1 & 1][1]);
            opt[i & 1][1] = opt[i - 1 & 1][0] + nums[i];
        }

        return Math.max(opt[nums.length - 1 & 1][0], opt[nums.length - 1 & 1][1]);
    }
}
