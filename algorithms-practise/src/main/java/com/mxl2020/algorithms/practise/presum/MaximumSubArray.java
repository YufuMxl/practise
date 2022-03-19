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
        prefixSumArray[0] = 0;
        for (int i = 1; i < prefixSumArray.length; i++) {
            prefixSumArray[i] = prefixSumArray[i - 1] + nums[i - 1];
        }

        // 求子数组的最大和
        int minPrefixSum = 0;
        int maxSumOfSubArray = -10000;
        for (int i = 1; i < prefixSumArray.length; i++) {
            minPrefixSum = Math.min(minPrefixSum, prefixSumArray[i - 1]);
            maxSumOfSubArray = Math.max(maxSumOfSubArray, prefixSumArray[i] - minPrefixSum);
        }

        return maxSumOfSubArray;
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
}
