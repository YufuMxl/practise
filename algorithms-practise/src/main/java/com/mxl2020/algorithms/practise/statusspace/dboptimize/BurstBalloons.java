package com.mxl2020.algorithms.practise.statusspace.dboptimize;

/**
 * 戳气球
 *
 * @see <a href="https://leetcode.cn/problems/burst-balloons/">LeetCode 312</a>
 */
public class BurstBalloons {


    /**
     * @param nums 每个元素对应着气球上的数字
     * @return 计算戳破所有的气球，能获得硬币的最大数量
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 将 nums 复制到 newNums 中，且令 newNums 首尾等于 1
        int[] newNums = new int[n + 2];
        System.arraycopy(nums, 0, newNums, 1, n);
        newNums[0] = 1;
        newNums[n + 1] = 1;

        // 本题的变量：nums 数组、最大硬币数
        // 可以用"区间"来描述一个数组的状态空间
        // opt[l][r]：在 nums 中从下标 l 到下标 r 这段闭区间内能获得的最大硬币数
        int[][] opt = new int[n + 2][n + 2];

        // 从最小区间开始计算 opt
        for (int len = 1; len <= n; len++) {
            for (int l = 1; l <= n - len + 1; l++) {
                int r = l + len - 1;
                // 遍历 l r 区间内的每一个气球 p
                // 决策：戳完最后一个气球 p 能获得的最大硬币数
                for (int p = l; p <= r; p++) {
                    opt[l][r] = Math.max(opt[l][r], opt[l][p - 1] + opt[p + 1][r] + newNums[l - 1] * newNums[r + 1] * newNums[p]);
                }
            }
        }

        return opt[1][n];
    }
}
