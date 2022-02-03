package com.mxl2020.algorithms.practise.stackandqueue.datastructure;

/**
 * 基于数组实现的顺序队列
 */
public class QueueBasedOnArray {
    private final Object[] items;
    // 数组大小
    private final int n;
    // 队头下标
    private int head = 0;
    // 队尾下标（指向空元素）
    private int tail = 0;

    // 申请一个大小为 capacity 的数组
    public QueueBasedOnArray(int capacity) {
        items = new Object[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(Object item) {
        // tail == n 表示队列末尾没有空间了
        if (tail == n) {
            // tail == n && head == 0，表示整个队列都占满了
            if (head == 0) return false;
            // 数据搬移
            for (int i = head; i < tail; ++i) {
                items[i - head] = items[i];
            }
            // 搬移完之后重新更新head和tail
            tail -= head;
            head = 0;
        }

        items[tail] = item;
        ++tail;
        return true;
    }

    // 出队
    public Object dequeue() {
        // 如果 head == tail 表示队列为空
        if (head == tail) return null;
        Object ret = items[head];
        ++head;
        return ret;
    }
}