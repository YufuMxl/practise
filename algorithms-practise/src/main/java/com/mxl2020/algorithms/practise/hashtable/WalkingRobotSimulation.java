package com.mxl2020.algorithms.practise.hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * 模拟行走机器人
 *
 * @see <a href="https://leetcode-cn.com/problems/walking-robot-simulation/">LeetCode 874</a>
 */
public class WalkingRobotSimulation {

    public int robotSim(int[] commands, int[][] obstacles) {
        final Set<String> obstacleSet = new HashSet<>(obstacles.length);
        for (int[] obstacle : obstacles) obstacleSet.add(obstacle[0] + ":" + obstacle[1]);

        final int[] position = new int[2];
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int currentDirection = 0;
        int maxDistance = 0;
        for (int command : commands) {
            if (command == -1) currentDirection = (currentDirection + 1) % 4;
            else if (command == -2) currentDirection = (currentDirection + 3) % 4;
            else {
                for (int i = 0; i < command; i++) {
                    int xForward = position[0] + directions[currentDirection][0];
                    int yForward = position[1] + directions[currentDirection][1];
                    if (obstacleSet.contains(xForward + ":" + yForward)) break;
                    else {
                        position[0] = xForward;
                        position[1] = yForward;
                    }
                }
                maxDistance = Math.max(maxDistance, position[0] * position[0] + position[1] * position[1]);
            }
        }
        return maxDistance;
    }

}
