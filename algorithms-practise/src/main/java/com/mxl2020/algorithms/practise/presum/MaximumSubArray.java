package com.mxl2020.algorithms.practise.presum;

/**
 * 最大子数组和
 *
 * @see <a href="https://leetcode-cn.com/problems/maximum-subarray/">LeetCode 53</a>
 */
public class MaximumSubArray {

    /**
     * 前缀和解法
     *
     * @param nums 整数数组（包含负数）
     * @return 最大的子数组之和
     */
    public int maxSubArray(int[] nums) {
        // 获取前缀和数组
        int[] prefixSumArray = new int[nums.length + 1];
        for (int i = 1; i < prefixSumArray.length; i++) {
            prefixSumArray[i] = prefixSumArray[i - 1] + nums[i - 1];
        }

        // 求子数组的最大和
        int minPrefixSum = 0;
        int ans = -(int) 1e9;
        for (int i = 1; i < prefixSumArray.length; i++) {
            ans = Math.max(ans, prefixSumArray[i] - minPrefixSum);
            minPrefixSum = Math.min(prefixSumArray[i], minPrefixSum);
        }

        return ans;
    }

    /**
     * 贪心解法
     * TODO 理解贪心解法
     */
    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) sum += num;
            else sum = num;

            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 动态规划解法
     */
    public int maxSubArray3(int[] nums) {
        int[] opt = new int[nums.length];
        opt[0] = nums[0];
        int maxSubArray = opt[0];
        for (int i = 1; i < opt.length; i++) {
            opt[i] = opt[i - 1] <= 0 ? nums[i] : opt[i - 1] + nums[i];
            if (opt[i] > maxSubArray) maxSubArray = opt[i];
        }

        return maxSubArray;
    }
}
