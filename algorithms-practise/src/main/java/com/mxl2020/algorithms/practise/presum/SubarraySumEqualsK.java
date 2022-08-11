package com.mxl2020.algorithms.practise.presum;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为 K 的子数组
 *
 * @see <a href="https://leetcode-cn.com/problems/subarray-sum-equals-k/">LeetCode 560</a>
 */
public class SubarraySumEqualsK {

    /**
     * 一维前缀和 + 两数之和
     *
     * @param nums 整数数组
     * @param k    目标值
     * @return 返回和为 K 的子数组的数量
     */
    public int subarraySum(int[] nums, int k) {
        // 求一维前缀和数组
        int[] prefixSumArray = new int[nums.length + 1];
        for (int i = 1; i < prefixSumArray.length; i++) {
            prefixSumArray[i] = prefixSumArray[i - 1] + nums[i - 1];
        }

        int count = 0;
        Map<Integer, Integer> prefixSumToCount = new HashMap<>();
        for (int prefixSum : prefixSumArray) {
            count += prefixSumToCount.getOrDefault(prefixSum, 0);
            prefixSumToCount.put(prefixSum + k, prefixSumToCount.getOrDefault(prefixSum + k, 0) + 1);
        }
        return count;
    }

}
