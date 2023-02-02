package com.mxl2020.algorithms.practise.search.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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
        int[] inDegree = new int[numCourses];
        List<List<Integer>> adjacencyArray = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjacencyArray.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            int fromCourse = prerequisite[1];
            int toCourse = prerequisite[0];
            adjacencyArray.get(fromCourse).add(toCourse);
            inDegree[toCourse]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int numOfLearnedCourses = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            numOfLearnedCourses++;
            for (int child : adjacencyArray.get(course)) {
                if (--inDegree[child] == 0) queue.offer(child);
            }
        }

        return numOfLearnedCourses == numCourses;
    }

}
