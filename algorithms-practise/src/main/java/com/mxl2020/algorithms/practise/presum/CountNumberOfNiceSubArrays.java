package com.mxl2020.algorithms.practise.presum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 统计「优美子数组」
 *
 * @see <a href="https://leetcode-cn.com/problems/count-number-of-nice-subarrays/">LeetCode 1248</a>
 */
public class CountNumberOfNiceSubArrays {
    /**
     * 前缀和（凡是遇到子数组与总和的问题，就思考能不能用"前缀和的子段和"思想解决问题）
     * <p>将奇数转为 1，偶数转为 0，计算奇偶数的前缀和数组</p>
     * <p>求子段和等于 k 的数量（利用 Map 可以快速解"两数之差"问题）</p>
     * <p></p>
     *
     * @param nums 整数数组
     * @param k    整数
     * @return 返回 nums 的子数组的数目，以使子数组包含 k 个奇数
     */
    public int numberOfSubArrays(int[] nums, int k) {
        // 初始化前缀和数组
        int[] prefixSumArray = new int[nums.length + 1];
        prefixSumArray[0] = 0;
        for (int i = 1; i < prefixSumArray.length; i++) {
            prefixSumArray[i] = prefixSumArray[i - 1] + (nums[i - 1] % 2);
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < prefixSumArray.length; i++) {
            int sum = prefixSumArray[i] + k;
            if (map.containsKey(sum)) {
                map.get(sum).add(i);
            } else {
                ArrayList<Integer> indices = new ArrayList<>();
                indices.add(i);
                map.put(sum, indices);
            }
        }

        // 寻找总和等于 k 的子段数量
        int count = 0;
        for (final int prefixSum : prefixSumArray) {
            // 子段和：sum(l,r) = S[r] - S[l-1] = k
            if (map.get(prefixSum) != null) {
                count += map.get(prefixSum).size();
            }
        }
        return count;
    }
}



