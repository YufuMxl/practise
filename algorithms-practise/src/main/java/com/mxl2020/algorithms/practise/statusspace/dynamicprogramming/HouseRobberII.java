package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 打家劫舍 II
 *
 * @see <a href="https://leetcode-cn.com/problems/house-robber-ii/">LeetCode 213</a>
 */
public class HouseRobberII {

    /**
     * @param nums 非负整数数组，代表每个房子藏匿的现金
     * @return 返回能偷盗的最大现金数量（不能连续偷相邻的两家，且不能连续偷第一家和最后一家）
     */
    public int rob(int[] nums) {
        final int n = nums.length;
        if (n == 1) return nums[0];
        // opt 的第二维下标，0 代表不偷，1 代表偷
        int[][] notRobLastHouseOpt = new int[n][2];
        notRobLastHouseOpt[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            notRobLastHouseOpt[i][0] = Math.max(notRobLastHouseOpt[i - 1][0], notRobLastHouseOpt[i - 1][1]);
            notRobLastHouseOpt[i][1] = notRobLastHouseOpt[i - 1][0] + nums[i];
        }

        // opt 的第二维下标，0 代表不偷，1 代表偷
        int[][] robLastHouseOpt = new int[n][2];
        robLastHouseOpt[0][1] = (int) -1e9;
        for (int i = 1; i < n; i++) {
            robLastHouseOpt[i][0] = Math.max(robLastHouseOpt[i - 1][0], robLastHouseOpt[i - 1][1]);
            robLastHouseOpt[i][1] = robLastHouseOpt[i - 1][0] + nums[i];
        }

        final int lastIndex = n - 1;
        return Math.max(notRobLastHouseOpt[lastIndex][0], robLastHouseOpt[lastIndex][1]);
    }
}
