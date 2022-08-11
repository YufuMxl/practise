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
        Map<String, Integer> subdomainToCountMap = new HashMap<>(cpdomains.length * 3);

        for (String cpdomain : cpdomains) {
            String[] splitCpdomain = cpdomain.split(" ");
            int count = Integer.parseInt(splitCpdomain[0]);
            String[] splitDomain = splitCpdomain[1].split("\\.");

            String cur = splitDomain[splitDomain.length - 1];
            subdomainToCountMap.put(cur, subdomainToCountMap.getOrDefault(cur, 0) + count);
            for (int i = splitDomain.length - 2; i >= 0; i--) {
                cur = splitDomain[i] + "." + cur;
                subdomainToCountMap.put(cur, subdomainToCountMap.getOrDefault(cur, 0) + count);
            }
        }

        List<String> result = new ArrayList<>(subdomainToCountMap.size());
        for (String key : subdomainToCountMap.keySet()) {
            result.add(subdomainToCountMap.get(key) + " " + key);
        }
        return result;
    }
}
