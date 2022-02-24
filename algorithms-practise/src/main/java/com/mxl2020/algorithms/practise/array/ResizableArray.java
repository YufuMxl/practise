package com.mxl2020.algorithms.practise.array;

/**
 * 设计一个变长数组
 * <p>支持索引与随机访问</p>
 * <p>默认常数的连续空间</p>
 * <p>扩容 + 缩容</p>
 */
public class ResizableArray<E> {

    private Object[] elementData;
    private int size = 0;   // 记录数组实际长度
    private int capacity;   // 记录数组当前容量

    public ResizableArray() {
        // 初始化一个常数空间的空数组
        elementData = new Object[10];
        capacity = 10;
    }

    public ResizableArray(int initialCapacity) {
        if (initialCapacity >= 0) {
            elementData = new Object[initialCapacity];
            capacity = initialCapacity;
        } else {
            elementData = new Object[0];
            capacity = 0;
        }
    }

    /**
     * 若空间不够，重新申请 2 倍大小的数组；旧数据拷贝到新数组，然后释放旧数组的空间
     * <p>均摊时间复杂度为 O(1)</p>
     */
    public void pushBack(E e) {
        elementData[size] = e;
    }

    /**
     * 若空间利用率（size/capacity）不到 25%，则释放一半空间
     * <p>均摊时间复杂度为 O(1)</p>
     */
    @SuppressWarnings("unchecked")
    public E popBack(int index) {
        return (E) elementData[index];
    }

}
