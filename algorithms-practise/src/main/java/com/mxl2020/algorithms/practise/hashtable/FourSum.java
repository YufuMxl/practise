package com.mxl2020.algorithms.practise.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 *
 * @see <a href="https://leetcode-cn.com/problems/4sum/">LeetCode 18</a>
 */
public class FourSum {

    /**
     * @param nums   可重复元素的整数数组
     * @param target 目标和
     * @return 返回元素之和等于 target 的所有子数组集合，任意子数组之间的内容不能重复
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        final int n = nums.length;
        final long[] numbers = new long[n];
        for (int i = 0; i < n; i++) numbers[i] = nums[i];

        final List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n - 3; i++) {
            if (numbers[i] + numbers[i + 1] + numbers[i + 2] + numbers[i + 3] > target) return ans;
            if (numbers[i] + numbers[n - 1] + numbers[n - 2] + numbers[n - 3] < target) continue;
            if (i > 0 && numbers[i] == numbers[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                long ijSum = numbers[i] + numbers[j];
                if (ijSum + numbers[j + 1] + numbers[j + 2] > target) break;
                if (ijSum + numbers[n - 1] + numbers[n - 2] < target) continue;
                if (j > i + 1 && numbers[j] == numbers[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = ijSum + numbers[left] + numbers[right];
                    if (sum < target) left++;
                    else if (sum > target) right--;
                    else {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && numbers[left] == numbers[left + 1]) left++;
                        while (left < right && numbers[right] == numbers[right - 1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }

        return ans;
    }
}
