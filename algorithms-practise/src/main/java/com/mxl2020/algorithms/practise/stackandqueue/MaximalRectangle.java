package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最大矩形
 *
 * @see <a href="https://leetcode-cn.com/problems/maximal-rectangle/">LeetCode 85</a>
 */
public class MaximalRectangle {

    /**
     * 单调栈
     *
     * @param matrix 只包含 0 和 1 的矩阵
     * @return 返回最大子矩阵的面积
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int maxArea = 0;
        int[] heights = new int[matrix[0].length];
        for (char[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] == '1') {
                    heights[i] = heights[i] += 1;
                } else {
                    heights[i] = 0;
                }
            }
            maxArea = Math.max(largestRectangleArea(heights), maxArea);
        }
        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        // 初始化单调队列，用于存储单调增长的矩形
        Deque<Rectangle> rectangleStack = new ArrayDeque<>();

        for (int i = 0; i <= heights.length; i++) {
            int height = i == heights.length ? 0 : heights[i];
            if (rectangleStack.isEmpty() || rectangleStack.peek().height <= height) {
                rectangleStack.push(new Rectangle(height, 1));
            } else {
                int accumulatedWidth = 0;
                while (!rectangleStack.isEmpty() && rectangleStack.peek().height > height) {
                    Rectangle topRectangle = rectangleStack.pop();
                    accumulatedWidth += topRectangle.width;
                    maxArea = Math.max(topRectangle.height * accumulatedWidth, maxArea);
                }
                rectangleStack.push(new Rectangle(height, accumulatedWidth + 1));
            }
        }

        return maxArea;
    }

    static class Rectangle {
        public int height;
        public int width;

        public Rectangle(int height, int width) {
            this.height = height;
            this.width = width;
        }
    }
}
