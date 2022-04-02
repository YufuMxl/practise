package com.mxl2020.algorithms.practise.search.binarysearch;

/**
 * 分割数组的最大值
 *
 * @see <a href="https://leetcode-cn.com/problems/split-array-largest-sum/">LeetCode 410</a>
 */
public class SplitArrayLargestSum {

    /**
     * @param nums 非负整数数组
     * @param m    将数组切分为 m 段非空子数组
     * @return 将数组切分为 m 段的前提下，返回最小的数组之和
     */
    public int splitArray(int[] nums, int m) {
        this.nums = nums;
        this.m = m;

        // 定义答案区间
        int left = 0;
        int right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            int ans = left + ((right - left) >> 1);
            boolean isValidAns = validate(ans);
            if (isValidAns) {
                right = ans;
            } else {
                left = ans + 1;
            }
        }
        return left;
    }

    private int[] nums;
    private int m;

    private boolean validate(int ans) {
        int subArraySum = 0;
        int splitTime = 1;
        for (int num : nums) {
            if (subArraySum + num <= ans) {
                subArraySum += num;
            } else {
                splitTime++;
                subArraySum = num;
            }
        }
        return splitTime <= m;
    }
}
