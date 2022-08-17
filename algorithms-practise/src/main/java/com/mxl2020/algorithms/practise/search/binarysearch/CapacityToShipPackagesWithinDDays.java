package com.mxl2020.algorithms.practise.search.binarysearch;

/**
 * 在 D 天内送达包裹的能力
 *
 * @see <a href="https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/">LeetCode 1011</a>
 */
public class CapacityToShipPackagesWithinDDays {

    /**
     * @param weights 传送带上的货物，按 weights 顺序将货物传送上船。weights[i] 是第 i 个货物的重量
     * @param days    需要在 days 天内将所有货物运完
     * @return 返回船舶的最小运力（1 天至少运多重货物，才能在 days 天内将货物运完）
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (canLoad(weights, days, mid)) {
                if (mid - 1 >= left && canLoad(weights, days, mid - 1)) right = mid - 1;
                else return mid;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    private boolean canLoad(int[] weights, int days, int load) {
        int sum = 0;
        int shippingDays = 1;
        for (int weight : weights) {
            sum += weight;
            if (sum > load) {
                shippingDays++;
                sum = weight;
            }
        }
        return shippingDays <= days;
    }
}
