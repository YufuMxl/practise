package com.mxl2020.algorithms.practise.search;

/**
 * 二分查找
 */
public class BinarySearch {

    /**
     * 查找第一个值等于给定值的元素
     *
     * @param a 可包含重复元素的有序数组
     */
    public int bsearchFirstValue(int[] a, int value) {
        int lowIndex = 0;
        int highIndex = a.length - 1;

        while (lowIndex <= highIndex) {
            int midIndex = lowIndex + ((highIndex - lowIndex) >> 1);
            if (a[midIndex] < value) {
                lowIndex = midIndex + 1;
            } else if (a[midIndex] > value) {
                highIndex = midIndex - 1;
            } else {
                if (midIndex == 0 || a[midIndex - 1] != value) {
                    return midIndex;
                } else {
                    highIndex = midIndex - 1;
                }
            }
        }
        return -1;
    }
}
