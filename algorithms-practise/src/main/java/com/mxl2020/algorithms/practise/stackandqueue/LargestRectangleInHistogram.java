package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 柱状图中最大的矩形
 *
 * @see <a href="https://leetcode-cn.com/problems/largest-rectangle-in-histogram/">LeetCode 84</a>
 */
public class LargestRectangleInHistogram {

    /**
     * 利用单调递增栈
     *
     * @param heights 每个元素代表宽度为 1 的柱子
     * @return 返回柱状图中最大矩形的面积
     */
    public int largestRectangleArea(int[] heights) {
        Deque<Rectangle> rectangleStack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {
            int height = i == heights.length ? 0 : heights[i];
            int accumulatedWidth = 0;
            if (rectangleStack.isEmpty() || rectangleStack.peek().height <= height) {
                rectangleStack.push(new Rectangle(height, 1));
            } else {
                while (!rectangleStack.isEmpty() && rectangleStack.peek().height > height) {
                    Rectangle higherRectangle = rectangleStack.pop();
                    accumulatedWidth += higherRectangle.width;
                    maxArea = Math.max(maxArea, higherRectangle.height * accumulatedWidth);
                }
                rectangleStack.push(new Rectangle(height, accumulatedWidth + 1));
            }
        }
        return maxArea;
    }

    static class Rectangle {
        public final int height;
        public final int width;

        public Rectangle(int height, int width) {
            this.height = height;
            this.width = width;
        }
    }
}
