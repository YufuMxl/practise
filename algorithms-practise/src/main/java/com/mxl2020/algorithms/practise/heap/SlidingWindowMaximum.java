package com.mxl2020.algorithms.practise.heap;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 滑动窗口最大值
 *
 * @see <a href="https://leetcode-cn.com/problems/sliding-window-maximum/">LeetCode 239</a>
 */
public class SlidingWindowMaximum {

    // 单调队列维护的是一个候选集合，队头的数据较旧，队尾的数据较新（时间有单调性）
    // 候选项的某个属性也具有单调性
    // 确定递增递减的方法：考虑任意两个下标 j1 和 j2，且 j1 < j2，写出 j1 比 j2 优的条件
    // 排除冗余的关键：若 j1 比 j2 差，j1 比 j2 的生命周期还短，j1 就没什么卵用

    /**
     * 单调递减队列
     *
     * @param nums 整数数组
     * @param k    k 不大于 nums.length
     * @return 返回滑动窗口 k 中元素的最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 队列保存的是单调递减的元素
        // 为了保证单调递减，队尾要支持出队和入队，所以使用双端队列
        Deque<Integer> slidingWindowDeque = new ArrayDeque<>();
        int[] answer = new int[nums.length + 1 - k];

        for (int i = 0; i < nums.length; i++) {
            // 移除出界元素，保证队列中的元素都在滑动窗口的范围内
            while (!slidingWindowDeque.isEmpty() && slidingWindowDeque.peekFirst() <= (i - k)) slidingWindowDeque.pollFirst();
            // 维护单调递减队列
            while (!slidingWindowDeque.isEmpty() && nums[slidingWindowDeque.peekLast()] < nums[i]) slidingWindowDeque.pollLast();
            // 保存数组下标是为了判断队首元素是否出界
            slidingWindowDeque.offerLast(i);

            // 拿队首元素（队首元素是当前窗口的最大值）
            if (!slidingWindowDeque.isEmpty() && (i + 1 -k) >= 0) answer[i + 1 - k] = nums[slidingWindowDeque.peekFirst()];
        }
        return answer;
    }

    /**
     * 二叉堆：大顶堆
     */
    public int[] maxSlidingWindowWithHeap(int[] nums, int k) {
        final int n = nums.length;
        this.nums = nums;
        this.heap = new int[k];
        this.numIndexToHeapIndex = new int[n];
        for (int i = 0; i < k; i++) {
            offer(i);
        }

        int[] result = new int[n - k + 1];
        result[0] = peek();

        for (int i = 1; i < result.length; i++) {
            remove(i - 1);
            offer(i + k - 1);
            result[i] = peek();
        }
        return result;
    }

    private int[] nums;
    private int[] heap;     // 存储 num 的下标
    private int size;
    private int[] numIndexToHeapIndex;

    private int peek() {
        return nums[heap[0]];
    }

    private void offer(int numIndex) {
        heap[size++] = numIndex;
        numIndexToHeapIndex[numIndex] = size - 1;
        heapifyUp(size - 1);
    }

    private void remove(int numIndex) {
        int heapIndex = numIndexToHeapIndex[numIndex];
        // 更新 heap size
        size--;
        // 如果删除的是 heap 的最后一个元素，直接返回即可
        if (heapIndex == size) return;

        heap[heapIndex] = heap[size];
        numIndexToHeapIndex[heap[heapIndex]] = heapIndex;

        heapifyUp(heapIndex);
        heapifyDown(heapIndex);
    }

    private void heapifyUp(int heapIndex) {
        if (heapIndex <= 0) return;
        int parentIndex = (heapIndex - 1) / 2;
        if (nums[heap[parentIndex]] >= nums[heap[heapIndex]]) return;

        int parentValue = heap[parentIndex];
        heap[parentIndex] = heap[heapIndex];
        heap[heapIndex] = parentValue;

        numIndexToHeapIndex[heap[parentIndex]] = parentIndex;
        numIndexToHeapIndex[heap[heapIndex]] = heapIndex;

        heapifyUp(parentIndex);
    }

    private void heapifyDown(int heapIndex) {
        int left = heapIndex * 2 + 1;
        if (left > size - 1) return;
        int right = left + 1;
        int maxChildIndex = right <= size - 1 && nums[heap[right]] > nums[heap[left]] ? right : left;

        if (nums[heap[heapIndex]] >= nums[heap[maxChildIndex]]) return;
        int maxChildValue = heap[maxChildIndex];
        heap[maxChildIndex] = heap[heapIndex];
        heap[heapIndex] = maxChildValue;

        numIndexToHeapIndex[heap[maxChildIndex]] = maxChildIndex;
        numIndexToHeapIndex[heap[heapIndex]] = heapIndex;

        heapifyDown(maxChildIndex);
    }
}
