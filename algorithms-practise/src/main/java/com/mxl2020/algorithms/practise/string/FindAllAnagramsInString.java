package com.mxl2020.algorithms.practise.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到字符串中所有字母异位词
 *
 * @see <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/">LeetCode 438</a>
 */
public class FindAllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) return ans;

        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pCnt[p.charAt(i) - 'a']++;
            sCnt[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pCnt, sCnt)) ans.add(0);

        for (int l = 1; l < s.length() - p.length() + 1; l++) {
            int r = l + p.length() - 1;
            if (s.charAt(l - 1) == s.charAt(r)) {
                if (!ans.isEmpty() && ans.get(ans.size() - 1) == l - 1) ans.add(l);
                continue;
            }
            sCnt[s.charAt(l - 1) - 'a']--;
            sCnt[s.charAt(r) - 'a']++;
            if (Arrays.equals(pCnt, sCnt)) ans.add(l);
        }

        return ans;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if (n < m) return res;

        int[] pCnt = new int[26];
        int[] sCnt = new int[26];

        for (int i = 0; i < m; i++) {
            pCnt[p.charAt(i) - 'a']++;
        }

        int left = 0;
        for (int right = 0; right < n; right++) {
            int curRight = s.charAt(right) - 'a';
            sCnt[curRight]++;
            while (sCnt[curRight] > pCnt[curRight]) {
                sCnt[s.charAt(left) - 'a']--;
                left++;
            }
            if (right - left + 1 == m) {
                res.add(left);
            }
        }
        return res;
    }
}
