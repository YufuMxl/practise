package com.mxl2020.algorithms.practise.string.basequestion;

import java.util.HashSet;
import java.util.Set;

/**
 * 宝石与石头
 *
 * @see <a href="https://leetcode.cn/problems/jewels-and-stones/">LeetCode 771</a>
 */
public class JewelsAndStones {

    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelSet = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            jewelSet.add(c);
        }
        int ans = 0;
        for (char c : stones.toCharArray()) {
            if (jewelSet.contains(c)) ans++;
        }
        return ans;
    }
}
