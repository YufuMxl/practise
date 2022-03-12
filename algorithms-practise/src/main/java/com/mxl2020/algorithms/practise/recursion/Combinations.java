package com.mxl2020.algorithms.practise.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 *
 * @see <a href="https://leetcode-cn.com/problems/combinations/">LeetCode 77</a>
 */
public class Combinations {

    private List<List<Integer>> combinations = new ArrayList<>();

    /**
     * @param n 代表数组 [1, 2, ..., n]
     * @param k 代表组合的大小，比如当 k = 2 时，[1, 2] 就是一个组合
     * @return 返回所有组合的可能
     */
    public List<List<Integer>> combine(int n, int k) {
        generateCombinations(1, n, k);
        return combinations;
    }

    private void generateCombinations(int currentNum, int endNum, int combinationSize) {
        // 终止条件
        if (combinationSize <= 0) {
            combinations.add(new ArrayList<>());
            return;
        }
        if (currentNum + combinationSize > endNum) {
            List<Integer> combination = new ArrayList<>();
            for (int i = currentNum; i <= endNum; i++) {
                combination.add(i);
            }
            combinations.add(combination);
            return;
        }

        // 处理子问题
        generateCombinations(currentNum + 1, endNum, combinationSize - 1);
        // 处理当前问题
        for (List<Integer> combination : combinations) {
            combination.add(currentNum);
        }
        generateCombinations(currentNum + 1, endNum, combinationSize);
    }
}
