package com.mxl2020.algorithms.practise.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * 课程表
 *
 * @see <a href="https://leetcode-cn.com/problems/course-schedule/">LeetCode 207</a>
 */
public class CourseSchedule {
    /**
     * 广度优先遍历：拓扑排序
     *
     * @param numCourses    课程总数
     * @param prerequisites 课程的先修关系，相当于有向图（课程编号从 0 到 numCourses - 1）
     * @return 判断给定的 prerequisites 是否能学完所有课程（判断有向图是否有环）
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 初始化"入度表"
        int[] inDegree = new int[numCourses];
        // 初始化"拓扑排序表"
        ArrayList<Integer> visited = new ArrayList<>(numCourses);
        // 初始化"出边数组"
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(i, new ArrayList<>());
        }
        // 存储"有向图"
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }
        // 广度优先遍历
        Queue<Integer> courseQueue = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            // 寻找入度为 0 的课程，将其放入队列
            if (inDegree[i] == 0) courseQueue.offer(i);
        }
        while (!courseQueue.isEmpty()) {
            int course = courseQueue.poll();
            visited.add(course);
            for (int child : graph.get(course)) {
                inDegree[child]--;
                if (inDegree[child] == 0) courseQueue.offer(child);
            }
        }

        // 如果 visited size 与节点数量相等，则该图无环
        return visited.size() == numCourses;
    }
}
