package com.mxl2020.algorithms.practise.search.binarysearch;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * @see <a href="https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/">LeetCode 34</a>
 */
public class FindFirstAndLastElementInSortedArray {

    /**
     * @param nums   有重复元素的升序数组
     * @param target 目标值
     * @return 返回目标值第一次出现和最后一次出现的下标，不存在则返回 [-1, -1]
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};

        // 查找 target 第一次出现的位置
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == 0 || nums[mid - 1] < nums[mid]) {
                    ans[0] = mid;
                    break;
                } else {
                    right = mid - 1;
                }
            }
        }

        // 查找 target 最后一次出现的位置
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] > nums[mid]) {
                    ans[1] = mid;
                    break;
                } else {
                    left = mid + 1;
                }
            }
        }
        return ans;
    }

    public int[] searchRange2(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 取中间值
            int mid = left + ((right - left) >> 1);

            if (nums[mid] < target) left = mid + 1;
            else if (nums[mid] > target) right = mid - 1;
            else {
                if (mid == 0 || nums[mid - 1] < nums[mid]) {
                    // 第一次出现的位置
                    ans[0] = mid;
                    // 最后一次出现的位置
                    int last = mid;
                    while (nums[last] == target) {
                        ans[1] = last;
                        if (last < nums.length - 1) last++;
                        else break;
                    }
                    break;
                } else right = mid - 1;
            }
        }
        return ans;
    }

}
