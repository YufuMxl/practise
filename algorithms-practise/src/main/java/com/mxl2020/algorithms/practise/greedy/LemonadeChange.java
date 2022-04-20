package com.mxl2020.algorithms.practise.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * 柠檬水找零
 *
 * @see <a href="https://leetcode-cn.com/problems/lemonade-change/">LeetCode 860</a>
 */
public class LemonadeChange {

    /**
     * @param bills 账单
     * @return 是否能给所有的顾客找零
     */
    public boolean lemonadeChange(int[] bills) {
        for (int bill : bills) {
            if (!changeable(bill)) return false;
            purse.put(bill, purse.getOrDefault(bill, 0) + 1);
        }
        return true;
    }

    private boolean changeable(final int bill) {
        int changeAmount = bill - 5;
        // 贪心找零
        for (int note : noteSet) {
            while (changeAmount >= note && purse.getOrDefault(note, 0) > 0) {
                purse.put(note, purse.get(note) - 1);
                changeAmount -= note;
            }
        }
        return changeAmount == 0;
    }

    private final Map<Integer, Integer> purse = new HashMap<>(3);

    private final int[] noteSet = {10, 5};
}
