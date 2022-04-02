package com.mxl2020.algorithms.practise.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 合并区间
 *
 * @see <a href="https://leetcode-cn.com/problems/merge-intervals/">LeetCode 56</a>
 */
public class MergeIntervals {

    /**
     * 解法一：排序
     *
     * @param intervals 若干个区间的集合，其中单个区间为 intervals[i] = [start_i, end_i]
     * @return 返回"不重叠的区间"的集合
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] < o2[0] || (o1[0] == o2[0] && o1[1] < o2[1])) return -1;
            else if (o1[0] == o2[0] && o1[1] == o2[1]) return 0;
            else return 1;
        });

        final ArrayList<int[]> ans = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                if (intervals[i][1] > end) end = intervals[i][1];
            } else {
                ans.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        ans.add(new int[]{start, end});
        return ans.toArray(new int[0][0]);
    }

    /**
     * 解法二：查分
     */
    public int[][] merge2(int[][] intervals) {
        return null;
    }
}
