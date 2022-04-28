package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

/**
 * 乘积最大子数组
 *
 * @see <a href="https://leetcode-cn.com/problems/maximum-product-subarray/">LeetCode 152</a>
 */
public class MaximumProductSubarray {

    /**
     * @param nums 整数数组（包含负数）
     * @return 返回乘积最大子数组的乘积值
     */
    public int maxProduct(int[] nums) {
        int[] minOpt = new int[nums.length];
        int[] maxOpt = new int[nums.length];
        minOpt[0] = nums[0];
        maxOpt[0] = nums[0];
        int maxProduct = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int aProduct = minOpt[i - 1] * nums[i];
            int bProduct = maxOpt[i - 1] * nums[i];
            minOpt[i] = Math.min(nums[i], Math.min(aProduct, bProduct));
            maxOpt[i] = Math.max(nums[i], Math.max(aProduct, bProduct));

            if (maxProduct < maxOpt[i]) maxProduct = maxOpt[i];
        }

        return maxProduct;
    }
}
