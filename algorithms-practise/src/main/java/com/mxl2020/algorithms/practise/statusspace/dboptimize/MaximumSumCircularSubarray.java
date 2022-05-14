package com.mxl2020.algorithms.practise.statusspace.dboptimize;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 环形子数组的最大和
 *
 * @see <a href="https://leetcode.cn/problems/maximum-sum-circular-subarray/">LeetCode 918</a>
 */
public class MaximumSumCircularSubarray {

    /**
     * 方法一：求 double 数组的前缀和 + 滑动窗口
     *
     * @param nums 环形数组（首尾相接）；子数组为环形数组的一段连续区间，其长度不能超过环形数组的大小，且不能为空
     * @return 返回环形数组中的最大子数组和
     */
    public int maxSubarraySumCircular(int[] nums) {
        final int n = nums.length;
        // 计算前缀和数组
        final int[] preSum = new int[n * 2 + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[(i - 1) % n];
        }

        int maxSum = (int) -1e9;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i < preSum.length; i++) {
            // 移除过期元素
            while (!deque.isEmpty() && i - deque.peekFirst() > n) {
                deque.pollFirst();
            }
            // 保持队列的单调递减
            while (!deque.isEmpty() && preSum[deque.peekLast()] >= preSum[i - 1]) {
                deque.pollLast();
            }
            // 入队
            deque.offerLast(i - 1);
            maxSum = Math.max(maxSum, preSum[i] - preSum[deque.peekFirst()]);
        }
        return maxSum;
    }
}
