package com.mxl2020.algorithms.practise.search.binarysearch;

import java.util.HashMap;
import java.util.Map;

/**
 * 在线选举
 *
 * @see <a href="https://leetcode.cn/problems/online-election/">LeetCode 911</a>
 */
public class TopVotedCandidate {

    private final int[] times;
    private final int[] topVotedPersons;

    /**
     * @param persons 第 i 张票属于 persons[i]
     * @param times   第 i 张票在 times[i] 时刻投出。times 是一个严格递增的有序数组。times.length == persons.length。
     */
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        this.topVotedPersons = new int[persons.length];

        Map<Integer, Integer> votes = new HashMap<>();
        votes.put(persons[0], 1);
        int topVotedPersonId = persons[0];
        topVotedPersons[0] = persons[0];

        for (int i = 1; i < persons.length; i++) {
            votes.put(persons[i], votes.getOrDefault(persons[i], 0) + 1);
            if (votes.get(persons[i]) >= votes.get(topVotedPersonId)) topVotedPersonId = persons[i];
            topVotedPersons[i] = topVotedPersonId;
        }
    }

    /**
     * @param t 给定时刻 t，可以确定投到第几张票了
     * @return 返回 t 时刻得票最多的人的编号；如果两人得票相同，返回截止到 t 时刻得到最后一票的人
     */
    public int q(int t) {
        return topVotedPersons[binarySearch(t)];
    }

    /**
     * @param t 给定时刻 t，找出 times 中第一个小于等于 t 的元素的下标
     */
    private int binarySearch(int t) {
        int left = 0;
        int right = times.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (times[mid] > t) right = mid - 1;
            else {
                if (mid + 1 <= right && times[mid + 1] <= t) left = mid + 1;
                else return mid;
            }
        }
        return -1;
    }
}
