package com.mxl2020.algorithms.practise.search.binarysearch;

/**
 * 猜数字大小
 *
 * @see <a href="https://leetcode-cn.com/problems/guess-number-higher-or-lower/">LeetCode 374</a>
 */
public class GuessNumberHigherOrLower {

    public int guessNumber(int n) {
        int left = 0;
        int right = n;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int guess(int num) {
        return 0;
    }
}
