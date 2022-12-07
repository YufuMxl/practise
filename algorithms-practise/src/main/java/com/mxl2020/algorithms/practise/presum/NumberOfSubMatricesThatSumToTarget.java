package com.mxl2020.algorithms.practise.presum;

import java.util.HashMap;
import java.util.Map;

/**
 * 元素和为目标值的子矩阵数量
 *
 * @see <a href="https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target/">LeetCode 1074</a>
 */
public class NumberOfSubMatricesThatSumToTarget {

    /**
     * 朴素二维前缀和
     *
     * @param matrix 矩阵
     * @param target 目标值
     * @return 返回元素和为 target 的子矩阵数量
     * sum(row1,cor1,row2,cor2) = s[row2][col2] - s[row2][col1 - 1] - s[row1 - 1][col2] + s[row1 - 1][col1 - 1] = target
     */
    public int numSubMatrixSumTarget(int[][] matrix, int target) {
        int[][] s = genPrefixSumArray(matrix);
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[0].length; j++) {
                for (int p = i + 1; p < s.length; p++) {
                    int tmp = s[i][j] - s[p][j];
                    for (int q = j + 1; q < s[0].length; q++) {
                        if (s[p][q] - s[i][q] + tmp == target) count++;
                    }
                }
            }
        }
        return count;
    }

    public int numSubMatrixSumTarget2(int[][] matrix, int target) {
        int[][] s = genPrefixSumArray(matrix);
        int count = 0;
        for (int top = 1; top < s.length; top++) {
            for (int bot = top; bot < s.length; bot++) {
                int cur;
                Map<Integer, Integer> map = new HashMap<>();
                for (int r = 1; r < s[0].length; r++) {
                    cur = s[bot][r] - s[top - 1][r];
                    if (cur == target) count++;
                    if (map.containsKey(cur - target)) count += map.get(cur - target);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return count;
    }

    private int[][] genPrefixSumArray(int[][] matrix) {
        int[][] s = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 1; i < s.length; i++) {
            for (int j = 1; j < s[0].length; j++) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        return s;
    }
}
