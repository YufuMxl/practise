package com.mxl2020.algorithms.practise.search.binarysearch;

/**
 * 爱吃香蕉的珂珂
 *
 * @see <a href="https://leetcode.cn/problems/koko-eating-bananas/">LeetCode 875</a>
 */
public class KokoEatingBananas {

    /**
     * @param piles n 堆香蕉，第 i 堆中有 piles[i] 根香蕉
     * @param h     警卫将离开 h 小时，h 小时后，珂珂不能再吃香蕉
     * @return 返回珂珂吃香蕉的最小速度，该速度要保证在警卫回来之前，珂珂吃完所有香蕉
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (!canEatUp(piles, h, mid)) left = mid + 1;
            else {
                if (mid - 1 >= left && canEatUp(piles, h, mid - 1)) right = mid - 1;
                else return mid;
            }
        }

        return -1;
    }

    private boolean canEatUp(final int[] piles, final int h, final int speed) {
        int eatingHours = 0;
        for (int pile : piles) {
            eatingHours += ((pile + speed - 1) / speed);
        }
        return eatingHours <= h;
    }

}


