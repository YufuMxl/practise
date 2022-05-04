package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 分割等和子集
 *
 * @see <a href="https://leetcode-cn.com/problems/partition-equal-subset-sum/">LeetCode 416</a>
 */
public class PartitionEqualSubsetSum {

    /**
     * @param nums 只包含正整数的非空数组
     * @return 判断 nums 数组是否可以分割成"元素总和相等"的两个子集
     */
    public boolean canPartition(int[] nums) {
        // 计算数组总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 和为奇数，返回 false
        if (sum % 2 == 1) return false;

        int targetSum = sum / 2;
        // opt[i][j] 表示从前 i 个数中选出一些数的和等于 j 这个命题是否为真
        boolean[][] opt = new boolean[nums.length][targetSum + 1];
        // 确定边界
        opt[0][0] = true;
        if (nums[0] <= targetSum) opt[0][nums[0]] = true;

        for (int i = 1; i < opt.length; i++) {
            int preNumIndex = i - 1;
            for (int j = 0; j < opt[0].length; j++) {
                // 不选第 i 个数
                opt[i][j] = opt[preNumIndex][j];
                // 选第 i 个数
                if (j >= nums[i]) opt[i][j] |= opt[preNumIndex][j - nums[i]];
            }
        }

        return opt[opt.length - 1][targetSum];
    }

    public boolean canPartition2(int[] nums) {
        // 计算数组总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 和为奇数，返回 false
        if (sum % 2 == 1) return false;

        int targetSum = sum / 2;
        // opt[i][j] 表示从前 i 个数中选出一些数的和等于 j 这个命题是否为真
        boolean[] opt = new boolean[targetSum + 1];
        // 确定边界
        opt[0] = true;
        if (nums[0] <= targetSum) opt[nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = targetSum; j >= nums[i]; j--) {
                opt[j] |= opt[j - nums[i]];
            }
        }

        return opt[targetSum];
    }
}
