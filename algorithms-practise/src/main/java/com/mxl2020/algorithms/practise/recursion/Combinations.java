package com.mxl2020.algorithms.practise.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 *
 * @see <a href="https://leetcode-cn.com/problems/combinations/">LeetCode 77</a>
 */
public class Combinations {

    /**
     * @param n 代表数组 [1, 2, ..., n]
     * @param k 代表组合的大小，比如当 k = 2 时，[1, 2] 就是一个组合
     * @return 返回所有组合的可能
     */
    public List<List<Integer>> combine(int n, int k) {
        return generateCombinations(1, n, k);
    }

    private List<List<Integer>> generateCombinations(final int currentNum, final int endNum, final int k) {
        // 终止条件
        if (k == 0) {
            List<List<Integer>> result = new ArrayList<>(1);
            result.add(new ArrayList<>());
            return result;
        }
        if (currentNum + k - 1 == endNum) {
            List<List<Integer>> result = new ArrayList<>(1);
            result.add(new ArrayList<>());
            for (int i = currentNum; i <= endNum; i++) {
                result.get(0).add(i);
            }
            return result;
        }

        List<List<Integer>> result1 = generateCombinations(currentNum + 1, endNum, k - 1);
        for (List<Integer> result : result1) {
            result.add(currentNum);
        }
        List<List<Integer>> result2 = generateCombinations(currentNum + 1, endNum, k);
        result2.addAll(result1);
        return result2;
    }

    // TODO 按照课程思路实现一遍
}
