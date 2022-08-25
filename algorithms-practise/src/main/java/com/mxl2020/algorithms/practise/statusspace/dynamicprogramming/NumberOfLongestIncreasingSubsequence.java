package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

import java.util.Arrays;

/**
 * 最长递增子序列的个数
 *
 * @see <a href="https://leetcode.cn/problems/number-of-longest-increasing-subsequence/">LeetCode 673</a>
 */
public class NumberOfLongestIncreasingSubsequence {

    /**
     * @param nums 未排序的整数数组 nums
     * @return 返回最长递增子序列的个数（严格递增）
     */
    public int findNumberOfLIS(int[] nums) {
        final int n = nums.length;
        // opt[i] 表示以 nums[i] 结尾的最长递增子序列的长度
        int[] opt = new int[n];
        // cnt[i] 表示以 nums[i] 结尾的最长递增子序列的个数
        int[] cnt = new int[n];
        Arrays.fill(opt, 1);
        Arrays.fill(cnt, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (opt[j] + 1 > opt[i]) {
                        opt[i] = opt[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (opt[j] + 1 == opt[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
        }

        int length = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (opt[i] > length) {
                length = opt[i];
                count = cnt[i];
            } else if (opt[i] == length) {
                count += cnt[i];
            }
        }

        return count;
    }
}
