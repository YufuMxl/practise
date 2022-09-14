package com.mxl2020.algorithms.practise.search.binarysearch;

import java.util.Arrays;

/**
 * 特殊数组的特征值
 *
 * @see <a href="https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/">LeetCode 1608</a>
 */
public class SpecialArray {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        final int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int x = length - mid;
            if (nums[mid] < x) left = mid + 1;
            else {
                if (mid == 0 || nums[mid - 1] < x) return x;
                else right = mid - 1;
            }
        }
        return -1;
    }
}
