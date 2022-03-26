package com.mxl2020.algorithms.practise.sort;

import java.util.HashMap;

/**
 * 数组的相对排序
 *
 * @see <a href="https://leetcode-cn.com/problems/relative-sort-array/">LeetCode 1122</a>
 */
public class RelativeSortArray {

    private int[] arr1;
    private HashMap<Integer, Integer> arr2Map;

    /**
     * 比较类排序
     *
     * @param arr1 被排序数组，有重复元素，
     * @param arr2 基准数组，无重复元素，元素都在 arr1 中出现过
     * @return 按照 arr2 的排序顺序，将 arr1 进行排序，将没有在 arr2 中出现的元素按照升序排在数组末尾
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        this.arr2Map = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            arr2Map.put(arr2[i], i);
        }
        quickSort(0, arr1.length - 1);
        return arr1;
    }

    private void quickSort(int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;
        int pivotIndex = partition(startIndex, endIndex);
        quickSort(startIndex, pivotIndex - 1);
        quickSort(pivotIndex + 1, endIndex);
    }

    private int partition(int startIndex, int endIndex) {
        int randomIndex = startIndex + (int) (Math.random() * (endIndex - startIndex + 1));
        int pivotValue = arr1[randomIndex];
        arr1[randomIndex] = arr1[endIndex];
        arr1[endIndex] = pivotValue;

        int i = startIndex;
        int j = endIndex - 1;
        while (i <= j) {
            if (!isALargerThanB(arr1[i], pivotValue)) i++;
            else {
                int tmp = arr1[j];
                arr1[j--] = arr1[i];
                arr1[i] = tmp;
            }
        }

        arr1[endIndex] = arr1[i];
        arr1[i] = pivotValue;
        return i;
    }

    // 比较函数，基于基准数组的排序顺序，比较两个数的大小
    private boolean isALargerThanB(int a, int b) {
        Integer numAOrder = arr2Map.get(a);
        Integer numBOrder = arr2Map.get(b);
        if (numAOrder != null && numBOrder != null) return numAOrder > numBOrder;
        else if (numAOrder == null && numBOrder == null) return a > b;
        else return numBOrder != null;
    }

}
