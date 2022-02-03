package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 * @see <a href="https://leetcode-cn.com/problems/implement-queue-using-stacks/">LeetCode 232</a>
 */
public class ImplementQueueUsingStacks {

    private final Stack<Integer> a;
    private final Stack<Integer> b;

    /**
     * 实现两个 stack：A & B
     */
    public ImplementQueueUsingStacks() {
        a = new Stack<>();
        b = new Stack<>();
    }

    /**
     * 入队操作：数据从 A 入栈
     */
    public void push(int x) {
        a.push(x);
    }

    /**
     * 出队操作：数据从 B 出栈；如果 B 为空，将 A 的所有数据压入 B，再从 B 出栈
     */
    public int pop() {
        if (b.isEmpty()) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
        }
        return b.pop();
    }

    /**
     * 查看队首元素
     */
    public int peek() {
        if (b.isEmpty()) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
        }
        return b.peek();
    }

    /**
     * 判断是否为空
     */
    public boolean empty() {
        return a.isEmpty() && b.isEmpty();
    }

}
