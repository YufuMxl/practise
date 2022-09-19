package com.mxl2020.algorithms.practise.sort;

import java.util.*;

/**
 * 按照频率将数组升序排序
 *
 * @see <a href="https://leetcode.cn/problems/sort-array-by-increasing-frequency/">LeetCode 1636</a>
 */
public class SortArrayByIncreasingFrequency {

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> keyToSizeMap = new HashMap<>();
        for (int num : nums) {
            keyToSizeMap.put(num, keyToSizeMap.getOrDefault(num, 0) + 1);
        }

        List<int[]> keyToSizeList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : keyToSizeMap.entrySet()) {
            keyToSizeList.add(new int[]{entry.getKey(), entry.getValue()});
        }

        keyToSizeList.sort((e1, e2) -> {
            if (e1[1] < e2[1]) return -1;
            else if (e1[1] == e2[1] && e1[0] > e2[0]) return -1;
            else return 1;
        });

        int index = 0;
        for (int[] keyToSize : keyToSizeList) {
            while (keyToSize[1] > 0) {
                nums[index++] = keyToSize[0];
                keyToSize[1]--;
            }
        }
        return nums;
    }
}
