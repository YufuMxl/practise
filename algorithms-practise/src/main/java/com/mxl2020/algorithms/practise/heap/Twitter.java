package com.mxl2020.algorithms.practise.heap;

import java.util.*;

/**
 * 设计推特
 *
 * @see <a href="https://leetcode.cn/problems/design-twitter/">LeetCode 355</a>
 */
public class Twitter {
    private final Map<Integer, Tweet> twitter;              // 用户 id 和推文（单链表）的对应关系
    private final Map<Integer, Set<Integer>> followings;    // 用户 id 和他关注的用户列表的对应关系
    private static PriorityQueue<Tweet> maxHeap;            // 合并 k 组推文使用的数据结构

    public Twitter() {
        followings = new HashMap<>();
        twitter = new HashMap<>();
        maxHeap = new PriorityQueue<>(Comparator.comparingLong(tweet -> -tweet.timestamp));
    }

    public void postTweet(int userId, int tweetId) {
        long timestamp =System.currentTimeMillis();
        if (twitter.containsKey(userId)) {
            Tweet oldHead = twitter.get(userId);
            Tweet newHead = new Tweet(tweetId, timestamp);
            newHead.next = oldHead;
            twitter.put(userId, newHead);
        } else {
            twitter.put(userId, new Tweet(tweetId, timestamp));
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        // 由于是全局使用的，使用之前需要清空
        maxHeap.clear();
        // 如果自己发了推文也要算上
        if (twitter.containsKey(userId)) maxHeap.offer(twitter.get(userId));

        Set<Integer> followingList = followings.get(userId);
        if (followingList != null && followingList.size() > 0) {
            for (Integer followingId : followingList) {
                Tweet tweet = twitter.get(followingId);
                if (tweet != null) maxHeap.offer(tweet);
            }
        }

        List<Integer> res = new ArrayList<>(10);
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            Tweet head = maxHeap.poll();
            res.add(head.id);

            // 这里最好的操作应该是 replace，但是 Java 没有提供
            if (head.next != null) maxHeap.offer(head.next);
            count++;
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        // 被关注人不能是自己
        if (followeeId == followerId) return;
        // 获取我自己的关注列表
        Set<Integer> followingList = followings.get(followerId);
        if (followingList == null) {
            Set<Integer> init = new HashSet<>();
            init.add(followeeId);
            followings.put(followerId, init);
        } else {
            if (followingList.contains(followeeId)) return;
            followingList.add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) return;
        // 获取我自己的关注列表
        Set<Integer> followingList = followings.get(followerId);
        if (followingList == null) return;
        // 这里删除之前无需做判断，因为查找是否存在以后，就可以删除，反正删除之前都要查找
        followingList.remove(followeeId);
    }

    static class Tweet {
        private final int id;           // 推文 id
        private final long timestamp;    // 发推文的时间戳
        private Tweet next;

        public Tweet(int id, long timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }
}
