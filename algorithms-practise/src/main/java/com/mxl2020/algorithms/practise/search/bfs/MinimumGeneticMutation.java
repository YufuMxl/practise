package com.mxl2020.algorithms.practise.search.bfs;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

/**
 * 最小基因变化
 *
 * @see <a href="https://leetcode-cn.com/problems/minimum-genetic-mutation/">LeetCode 433</a>
 */
public class MinimumGeneticMutation {

    /**
     * 基因序列中的一个字符改动，就是一次基因变化，且基因变化的结果必须属于基因库中的一种
     *
     * @param start 初始基因序列
     * @param end   最终基因序列
     * @param bank  基因库
     * @return 返回 start 变化成 end 的最少次数
     */
    public int minMutation(String start, String end, String[] bank) {
        // bankSet 功能类似于 visited 数组
        HashSet<String> bankSet = new HashSet<>(Arrays.asList(bank));
        bankSet.remove(start);
        Queue<Pair<String, Integer>> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new Pair<>(start, 0));
        while (!bfsQueue.isEmpty()) {
            Pair<String, Integer> from = bfsQueue.poll();

            for (String gene : bank) {
                if (bankSet.contains(gene) && isValidMutation(from.getKey(), gene)) {
                    if (end.equals(gene)) return from.getValue() + 1;
                    bfsQueue.offer(new Pair<>(gene, from.getValue() + 1));
                    bankSet.remove(gene);
                }
            }
        }
        return -1;
    }

    private boolean isValidMutation(String from, String to) {
        int diffCount = 0;
        for (int i = 0; i < 8; i++) {
            if (from.charAt(i) != to.charAt(i)) diffCount++;
        }
        return diffCount == 1;
    }
}
