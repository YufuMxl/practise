package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 接雨水
 *
 * @see <a href="https://leetcode-cn.com/problems/trapping-rain-water/">LeetCode 42</a>
 */
public class TrappingRainWater {

    /**
     * 利用单调递减栈
     * <p>
     * 用水的横条计算面积
     *
     * @param heights 每个元素代表宽度为 1 的柱子
     * @return 返回该柱状图可接雨水的最大量（最大面积）
     */
    public int trap(int[] heights) {
        Deque<Rectangle> rectangleStack = new ArrayDeque<>();
        int maxArea = 0;

        for (int height : heights) {
            if (rectangleStack.isEmpty() || rectangleStack.peek().height >= height) {
                rectangleStack.push(new Rectangle(height, 1));
            } else {
                int accumulatedWidth = 0;
                while (rectangleStack.peek().height < height) {
                    Rectangle lowerRectangle = rectangleStack.pop();
                    if (rectangleStack.isEmpty()) {
                        break;
                    }
                    int depth = Math.min(rectangleStack.peek().height, height) - lowerRectangle.height;
                    accumulatedWidth += lowerRectangle.width;
                    maxArea += accumulatedWidth * depth;
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
