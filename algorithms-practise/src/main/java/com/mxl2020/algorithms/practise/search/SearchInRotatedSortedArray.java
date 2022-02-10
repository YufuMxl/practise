package com.mxl2020.algorithms.practise.search;

/**
 * 搜索旋转排序数组
 *
 * @see <a href="https://leetcode-cn.com/problems/search-in-rotated-sorted-array/">LeetCode 33</a>
 */
public class SearchInRotatedSortedArray {

    /**
     * 二分查找
     * <p>
     * 时间复杂度 O(log n)
     * 空间复杂度 O(1)
     *
     * @param nums 比如 [4,5,6,7,0,1,2]
     * @return target 在数组中的下标
     */
    public int search(int[] nums, int target) {
        int arrayEndIndex = nums.length - 1;
        int minimumIndex = 0;
        int maximumIndex = arrayEndIndex;

        int lowIndex = 0;
        int highIndex = arrayEndIndex;
        // 判断 nums 是否为单调递增数组
        if (nums[minimumIndex] > nums[maximumIndex]) {
            // 如果不是单调递增数组，用二分查找法寻找最大值和最小值的下标
            while (lowIndex <= highIndex) {
                int midIndex = lowIndex + ((highIndex - lowIndex) >> 1);
                if (midIndex != 0 && nums[midIndex] < nums[midIndex - 1]) {
                    minimumIndex = midIndex;
                    maximumIndex = midIndex - 1;
                    break;
                } else if (midIndex != arrayEndIndex && nums[midIndex] > nums[midIndex + 1]) {
                    minimumIndex = midIndex + 1;
                    maximumIndex = midIndex;
                    break;
                } else if (nums[midIndex] >= nums[0]) {
                    lowIndex = midIndex + 1;
                } else {
                    highIndex = midIndex - 1;
                }
            }
        }

        // 寻找 target 所处的区间
        if (nums[0] <= target && target <= nums[maximumIndex]) {
            lowIndex = 0;
            highIndex = maximumIndex;
        } else {
            lowIndex = minimumIndex;
            highIndex = arrayEndIndex;
        }

        while (lowIndex <= highIndex) {
            int midIndex = lowIndex + ((highIndex - lowIndex) >> 1);
            if (nums[midIndex] == target) {
                return midIndex;
            } else if (nums[midIndex] > target) {
                highIndex = midIndex - 1;
            } else {
                lowIndex = midIndex + 1;
            }
        }
        // 未找到，返回 -1
        return -1;
    }

    public int search2(int[] nums, int target) {
        int endIndex = nums.length - 1;
        int lowIndex = 0;
        int highIndex = endIndex;

        while (lowIndex <= highIndex) {
            int midIndex = lowIndex + ((highIndex - lowIndex) >> 1);

            if (nums[midIndex] == target) {
                return midIndex;
            }

            if (nums[midIndex] >= nums[0]) {
                // midIndex 落在左侧的有序序列中
                if (target >= nums[0] && target < nums[midIndex]) {
                    // target 在 midIndex 左侧
                    highIndex = midIndex - 1;
                } else {
                    lowIndex = midIndex + 1;
                }
            } else {
                // midIndex 落在右侧的有序序列中
                if (target <= nums[endIndex] && target > nums[midIndex]) {
                    // target 在 midIndex 的右侧
                    lowIndex = midIndex + 1;
                } else {
                    highIndex = midIndex - 1;
                }
            }
        }

        return -1;
    }

}
