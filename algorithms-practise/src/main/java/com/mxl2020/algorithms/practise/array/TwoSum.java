package com.mxl2020.algorithms.practise.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @see <a href="https://leetcode-cn.com/problems/two-sum/">LeetCode 1</a>
 */
public class TwoSum {

    /**
     * 暴力解法
     * <p>
     * 时间复杂度 O(n^2)
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }

        }
        return new int[0];
    }

    /**
     * 哈希表
     * <p>
     * 时间复杂度 O(n)
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int otherNum = target - nums[i];
            if (numsMap.containsKey(otherNum)) {
                return new int[]{i, numsMap.get(otherNum)};
            } else {
                numsMap.put(nums[i], i);
            }
        }
        return new int[0];
    }
}
