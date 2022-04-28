package com.mxl2020.algorithms.practise.statusspace.dynamicprogramming;

import java.util.Arrays;

/**
 * 最长递增子序列
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-increasing-subsequence/">LeetCode 300</a>
 */
public class LongestIncreasingSubsequence {

    /**
     * @param nums 整数数组
     * @return 返回 LIS 的长度
     */
    public int lengthOfLIS(int[] nums) {
        // 确定状态 & 最优子结构：设 opt[i] 是以 nums[i] 结尾的序列的 LIS 长度
        int[] opt = new int[nums.length];
        // 记录转移路径
        int[] pre = new int[nums.length];
        // 确定边界
        Arrays.fill(opt, 1);
        Arrays.fill(pre, -1);
        for (int i = 1; i < opt.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && opt[i] < opt[j] + 1) {
                    opt[i] = opt[j] + 1;
                    pre[i] = j;
                }
            }
        }

        int lisLength = 1;
        int endNumIndex = 0;
        for (int i = 0; i < opt.length; i++) {
            if (lisLength < opt[i]) {
                lisLength = opt[i];
                endNumIndex = i;
            }
        }
        print(nums, pre, endNumIndex);
        return lisLength;
    }

    private void print(final int[] nums, final int[] pre, final int endNumIndex) {
        if (endNumIndex == -1) return;
        print(nums, pre, pre[endNumIndex]);
        System.out.println(nums[endNumIndex]);
    }
}
