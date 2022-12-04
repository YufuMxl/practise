package com.mxl2020.algorithms.practise.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子域名访问计数
 *
 * @see <a href="https://leetcode-cn.com/problems/subdomain-visit-count/">LeetCode 811</a>
 */
public class SubdomainVisitCount {
    /**
     * 散列映射
     *
     * @param cpdomains 计数配对域名，形如：9001 discuss.leetcode.com
     * @return 子域名的计数配对域名
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for (String cpdomain : cpdomains) {
            String[] splitCPDomain = cpdomain.split(" ");
            int count = Integer.parseInt(splitCPDomain[0]);
            String[] splitDomain = splitCPDomain[1].split("\\.");

            String subDomain = splitDomain[splitDomain.length - 1];
            map.put(subDomain, map.getOrDefault(subDomain, 0) + count);
            for (int i = splitDomain.length - 2; i >= 0; i--) {
                subDomain = splitDomain[i] + "." + subDomain;
                map.put(subDomain, map.getOrDefault(subDomain, 0) + count);
            }
        }

        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ans.add(entry.getValue() + " " + entry.getKey());
        }
        return ans;
    }
}
