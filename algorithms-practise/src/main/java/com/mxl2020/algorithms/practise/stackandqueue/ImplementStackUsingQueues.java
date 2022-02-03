package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 *
 * @see <a href="https://leetcode-cn.com/problems/implement-stack-using-queues/">LeetCode 225</a>
 */
public class ImplementStackUsingQueues {

    private final Queue<Integer> q1;
    private final Queue<Integer> q2;

    public ImplementStackUsingQueues() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /**
     * 入栈：将元素放入空队列，同时将另一个队列的所有元素放入该队列
     */
    public void push(int x) {
        if (q1.isEmpty()) {
            q1.offer(x);
            while (!q2.isEmpty()) {
                q1.offer(q2.poll());
            }
        } else {
            q2.offer(x);
            while (!q1.isEmpty()) {
                q2.offer(q1.poll());
            }
        }
    }

    /**
     * 出栈：从非空队列中获取元素
     */
    public int pop() {
        if (!q1.isEmpty()) {
            return q1.poll();
        } else if (!q2.isEmpty()) {
            return q2.poll();
        } else {
            return -1;
        }
    }

    public int top() {
        if (!q1.isEmpty()) {
            return q1.peek();
        } else if (!q2.isEmpty()) {
            return q2.peek();
        } else {
            return -1;
        }
    }

    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }

    public void pushWithOneQueue(int x) {
        int n = q1.size();
        q1.offer(x);
        for (int i = 0; i < n; i++) {
            q1.offer(q1.poll());
        }
    }
}
