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
        HashSet<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;
        bankSet.remove(start);

        Queue<Pair<String, Integer>> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new Pair<>(start, 0));
        while (!bfsQueue.isEmpty()) {
            Pair<String, Integer> from = bfsQueue.poll();

            String[] validGenes = bankSet.toArray(new String[0]);
            for (String gene : validGenes) {
                if (isValidMutation(from.getKey(), gene)) {
                    if (end.equals(gene)) return from.getValue() + 1;
                    else {
                        bfsQueue.offer(new Pair<>(gene, from.getValue() + 1));
                        bankSet.remove(gene);
                    }
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
