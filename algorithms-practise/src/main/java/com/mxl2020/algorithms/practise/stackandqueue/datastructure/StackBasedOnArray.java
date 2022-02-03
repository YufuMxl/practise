package com.mxl2020.algorithms.practise.stackandqueue.datastructure;

/**
 * 基于数组实现的顺序栈
 */
public class StackBasedOnArray {

    private final Object[] items;
    // 记录栈的大小
    private final int n;
    // 记录当前栈的元素总量
    private int count = 0;

    public StackBasedOnArray(int n) {
        this.items = new Object[n];
        this.n = n;
    }

    /**
     * 入栈操作
     */
    public boolean push(Object item) {
        if (this.count == this.n) {
            return false;
        }
        this.items[count] = item;
        ++count;
        return true;
    }

    /**
     * 出栈操作
     */
    public Object pop() {
        if (count == 0) {
            return null;
        }
        Object result = this.items[count - 1];
        --count;
        return result;
    }

}
