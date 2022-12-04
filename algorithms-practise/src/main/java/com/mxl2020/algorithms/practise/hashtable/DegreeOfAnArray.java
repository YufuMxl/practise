package com.mxl2020.algorithms.practise.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组的度
 *
 * @see <a href="https://leetcode-cn.com/problems/degree-of-an-array/">LeetCode 697</a>
 */
public class DegreeOfAnArray {

    /**
     * 散列映射
     *
     * @param nums 非空的非负整数数组
     * @return 返回与 nums 拥有相同大小的度的最短连续子数组（数组的"度"的定义是指数组里任一元素出现频数的最大值）
     */
    public int findShortestSubArray(int[] nums) {
        int maxDegree = 0;
        Map<Integer, CountRecord> counts = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            CountRecord countRecord = counts.getOrDefault(nums[i], new CountRecord(i));
            countRecord.count++;
            countRecord.endIndex = i;
            counts.put(nums[i], countRecord);
            maxDegree = Math.max(maxDegree, countRecord.count);
        }

        int subArrayMinLength = nums.length;
        for (CountRecord record : counts.values()) {
            if (record.count == maxDegree) subArrayMinLength = Math.min(subArrayMinLength, record.getLength());
        }
        return subArrayMinLength;
    }

    static class CountRecord {
        private int count = 0;
        private final int startIndex;
        private int endIndex;

        public int getLength() {
            return endIndex - startIndex + 1;
        }

        public CountRecord(int startIndex) {
            this.startIndex = startIndex;
        }
    }
}
