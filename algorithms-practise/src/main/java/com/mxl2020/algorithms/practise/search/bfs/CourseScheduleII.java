package com.mxl2020.algorithms.practise.search.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * 课程表 II
 *
 * @see <a href="https://leetcode-cn.com/problems/course-schedule-ii/">LeetCode 210</a>
 */
public class CourseScheduleII {

    /**
     * 广度优先遍历：拓扑排序
     *
     * @param numCourses    课程总数
     * @param prerequisites 课程的先修关系，相当于有向图（课程编号从 0 到 numCourses - 1）
     * @return 返回"学完所有课程"的拓扑排序；如果无法学完，返回空数组（比如图中有环）
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 初始化"入度表"
        int[] inDegree = new int[numCourses];
        // 初始化"拓扑排序表"
        int coursesIndex = 0;
        int[] courses = new int[numCourses];
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
            // 假设课程的总量不会很大，这里不 break 循环了
            if (inDegree[i] == 0) courseQueue.offer(i);
        }
        while (!courseQueue.isEmpty()) {
            int course = courseQueue.poll();
            courses[coursesIndex++] = course;
            for (int child : graph.get(course)) {
                if (--inDegree[child] == 0) courseQueue.offer(child);
            }
        }
        // 返回正确的课程表，或者返回空数组
        return coursesIndex == numCourses ? courses : new int[0];
    }
}
