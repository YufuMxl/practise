package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.Stack;

/**
 * 最小栈
 *
 * @see <a href="https://leetcode-cn.com/problems/min-stack/">LeetCode 155</a>
 */
public class MinStack {

    private final Stack<Integer> mainStack;
    private final Stack<Integer> minStack;

    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        mainStack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    public void pop() {
        mainStack.pop();
        minStack.pop();
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
