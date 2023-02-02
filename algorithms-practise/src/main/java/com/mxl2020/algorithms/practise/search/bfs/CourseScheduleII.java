package com.mxl2020.algorithms.practise.search.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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
        List<List<Integer>> adjacencyArray = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyArray.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            adjacencyArray.get(prerequisite[1]).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] order = new int[numCourses];
        int orderIndex = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[orderIndex++] = course;
            for (int child : adjacencyArray.get(course)) {
                if (--inDegree[child] == 0) queue.offer(child);
            }
        }
        return orderIndex == numCourses ? order : new int[0];
    }
}
