package com.mxl2020.algorithms.practise.presum;

/**
 * 航班预订统计
 *
 * @see <a href="https://leetcode-cn.com/problems/corporate-flight-bookings/">LeetCode 1109</a>
 */
public class CorporateFlightBookings {

    /**
     * 差分 + 前缀和
     *
     * @param bookings 航班预订表
     * @param n        航班编号（1,2...n）
     * @return seats[i] 为编号 i+1 的航班被预订的座位数
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // seats[i] 代表某个航班被预订的座位总数
        int[] seats = new int[n];

        // 计算 seats 的差分数组 delta
        int[] delta = new int[n + 1];
        for (int i = 0; i < bookings.length; i++) {
            int seatCount = bookings[i][2];
            int firstFlightIndex = bookings[i][0] - 1;
            int lastFlightIndex = bookings[i][1] - 1;
            delta[firstFlightIndex] += seatCount;
            delta[lastFlightIndex + 1] -= seatCount;
        }

        // 将差分数组通过前缀和转换为原始数组
        seats[0] = delta[0];
        for (int i = 1; i < n; i++) {
            seats[i] = seats[i - 1] + delta[i];
        }

        return seats;
    }
}
