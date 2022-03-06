package com.mxl2020.algorithms.practise.array;

/**
 * 盛最多水的容器
 *
 * @see <a href="https://leetcode-cn.com/problems/container-with-most-water/">LeetCode 11</a>
 */
public class ContainerWithMostWater {

    /**
     * 双指针扫描
     * <p>
     * 去掉两者中的较短者，双指针往中间靠拢，遍历过程中更新答案
     */
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxArea = 0;

        while (i < j) {
            maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            if (height[i] <= height[j]) i++;
            else j--;
        }
        return maxArea;
    }

}
