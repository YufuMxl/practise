package com.mxl2020.algorithms.practise.search.binarysearch;

/**
 * 搜索二维矩阵
 *
 * @see <a href="https://leetcode.cn/problems/search-a-2d-matrix/">LeetCode 74</a>
 */
public class Search2DMatrix {
    /**
     * @param matrix m x n 的矩阵。特点：
     *               每行中的整数从左到右按升序排列；
     *               每行的第一个整数大于前一行的最后一个整数
     * @param target 目标值
     * @return 判断矩阵中是否存在目标值
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        final int m = matrix.length;
        final int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int x = mid / n;
            int y = mid % n;
            if (matrix[x][y] < target) {
                left = mid + 1;
            } else if (matrix[x][y] > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
