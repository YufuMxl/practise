package com.mxl2020.algorithms.practise.tree.datastructure;

/**
 * 大顶堆
 */
public class Heap {
    // 数组，从下标 1 开始存储数据
    private final int[] a;
    // 堆可以存储的最大数据个数
    private final int capacity;
    // 堆中已经存储的数据个数
    private int count;

    public Heap(int capacity) {
        this.a = new int[capacity + 1];
        this.capacity = capacity;
        this.count = 0;
    }

    public void insert(int data) {
        // 堆满了
        if (count >= capacity) return;
        // 插入数据
        a[++count] = data;
        heapifyUp();
    }

    // 从下往上的堆化
    private void heapifyUp() {
        int lastIndex = count;
        while (lastIndex / 2 > 0 && a[lastIndex / 2] < a[lastIndex]) {
            int tmp = a[lastIndex];
            a[lastIndex] = a[lastIndex / 2];
            a[lastIndex / 2] = tmp;
            lastIndex = lastIndex / 2;
        }
    }

    public void removeMax() {
        // 堆中没有数据
        if (count <= 0) return;
        // 用堆中最后一个元素覆盖堆顶元素
        a[1] = a[count];
        // 删除堆中最后一个元素
        a[count--] = 0;

        heapifyDown(1);
    }

    // 从上往下的堆化
    private void heapifyDown(int index) {
        // 递归边界条件
        if (index * 2 > count) return;

        // 获取较大值的子节点的下标
        int maxChildIndex = index * 2;
        if (maxChildIndex + 1 <= count && a[maxChildIndex + 1] > a[maxChildIndex]) maxChildIndex++;

        // 与父节点交换元素
        if (a[index] < a[maxChildIndex]) {
            int maxChildValue = a[maxChildIndex];
            a[maxChildIndex] = a[index];
            a[index] = maxChildValue;
            heapifyDown(maxChildIndex);
        }
    }

}
