package com.mxl2020.algorithms.practise.stackandqueue.datastructure;

/**
 * 基于数组实现的循环队列
 */
public class QueueBasedOnArrayOfCircular {
    private final Object[] items;
    // 数组大小
    private final int n;
    // 队头下标
    private int head = 0;
    // 队尾下标（指向空元素）
    private int tail = 0;

    // 申请一个大小为 capacity 的数组
    public QueueBasedOnArrayOfCircular(int capacity) {
        items = new Object[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(Object item) {
        // 队列满了
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    // 出队
    public Object dequeue() {
        // 如果 head == tail 表示队列为空
        if (head == tail) return null;
        Object ret = items[head];
        head = (head + 1) % n;
        return ret;
    }
}
