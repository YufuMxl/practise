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
        int numsLength = nums.length;
        int[] answer = new int[numsLength - k + 1];

        for (int i = 0; i < numsLength; i++) {
            // 移除出界元素，保证队列中的元素都在滑动窗口的范围内
            while (!slidingWindowDeque.isEmpty() && slidingWindowDeque.peekFirst() <= (i - k)) {
                slidingWindowDeque.pollFirst();
            }
            // 维护单调递减队列
            while (!slidingWindowDeque.isEmpty() && nums[slidingWindowDeque.peekLast()] < nums[i]) {
                slidingWindowDeque.pollLast();
            }
            // 保存数组下标是为了判断队首元素是否出界
            slidingWindowDeque.offerLast(i);

            // 拿队首元素（队首元素是当前窗口的最大值）
            if (!slidingWindowDeque.isEmpty() && i + 1 >= k) {
                answer[i + 1 - k] = nums[slidingWindowDeque.peekFirst()];
            }
        }
        return answer;
    }

    /**
     * 大顶堆解法
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        this.heap = new int[k];
        this.numIndexToHeapIndex = new int[k];
        for (int i = 0; i < k; i++) {
            insertHeap(i);
        }

        int[] result = new int[nums.length - k + 1];
        result[0] = nums[heap[0]];

        for (int i = 1; i < result.length; i++) {
            removeKey(i - 1);
            insertHeap(i + k - 1);
            result[i] = nums[heap[0]];
        }
        return result;
    }

    private int[] nums;
    private int k;
    private int[] heap;
    private int heapSize;
    private int[] numIndexToHeapIndex;

    private void insertHeap(int numIndex) {
        heap[heapSize++] = numIndex;
        numIndexToHeapIndex[numIndex % k] = heapSize - 1;
        heapifyUp(heapSize - 1);
    }

    private void heapifyUp(int heapIndex) {
        int parentIndex = (heapIndex - 1) / 2;
        if (parentIndex >= 0 && nums[heap[heapIndex]] > nums[heap[parentIndex]]) {
            int parentValue = heap[parentIndex];
            heap[parentIndex] = heap[heapIndex];
            heap[heapIndex] = parentValue;

            numIndexToHeapIndex[heap[parentIndex] % k] = parentIndex;
            numIndexToHeapIndex[heap[heapIndex] % k] = heapIndex;

            heapifyUp(parentIndex);
        }
    }

    private void removeKey(int numIndex) {
        int heapIndex = numIndexToHeapIndex[numIndex % k];
        // 更新 heap size
        heapSize--;
        // 如果删除的是 heap 的最后一个元素，直接返回即可
        if (heapIndex == heapSize) return;

        heap[heapIndex] = heap[heapSize];
        numIndexToHeapIndex[heap[heapIndex] % k] = heapIndex;

        heapifyUp(heapIndex);
        heapifyDown(heapIndex);
    }

    private void heapifyDown(int heapIndex) {
        int left = heapIndex * 2 + 1;
        if (left > heapSize - 1) return;
        int right = left + 1;
        int maxChildIndex = right <= heapSize - 1 && nums[heap[right]] > nums[heap[left]] ? right : left;

        if (nums[heap[maxChildIndex]] > nums[heap[heapIndex]]) {
            int maxChildValue = heap[maxChildIndex];
            heap[maxChildIndex] = heap[heapIndex];
            heap[heapIndex] = maxChildValue;

            numIndexToHeapIndex[heap[maxChildIndex] % k] = maxChildIndex;
            numIndexToHeapIndex[heap[heapIndex] % k] = heapIndex;

            heapifyDown(maxChildIndex);
        }
    }
}
