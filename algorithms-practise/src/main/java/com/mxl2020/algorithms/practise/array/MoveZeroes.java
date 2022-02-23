package com.mxl2020.algorithms.practise.array;

/**
 * 移动零
 *
 * @see <a href="https://leetcode-cn.com/problems/move-zeroes/">LeetCode 283</a>
 */
public class MoveZeroes {

    /**
     * 双指针
     * <p>
     * 遍历数组，将非零的元素往数组前面部分排放
     * <p>
     * 时间复杂度 O(n)
     * <p>
     * 空间复杂度 O(1)
     *
     * @param nums 包含零的无序数组
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[n++] = nums[i];
            }
        }

        for (int i = n; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[n];
                nums[n++] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}
