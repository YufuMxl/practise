package com.mxl2020.algorithms.practise.presum;

/**
 * 二维区域和检索 - 矩阵不可变
 *
 * @see <a href="https://leetcode-cn.com/problems/range-sum-query-2d-immutable/">LeetCode 304</a>
 */
public class RangeSumQuery2DImmutable {

    private final int[][] s;

    public RangeSumQuery2DImmutable(int[][] matrix) {
        s = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i < s.length; i++) {
            for (int j = 1; j < s[0].length; j++) {
                s[i][j] = s[i][j - 1] + s[i - 1][j] - s[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++;
        col1++;
        row2++;
        col2++;
        return s[row2][col2] - s[row2][col1 - 1] - s[row1 - 1][col2] + s[row1 - 1][col1 - 1];
    }
}
