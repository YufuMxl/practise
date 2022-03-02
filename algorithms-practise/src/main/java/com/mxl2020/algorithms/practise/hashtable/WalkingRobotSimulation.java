package com.mxl2020.algorithms.practise.hashtable;

import java.util.HashSet;

/**
 * 模拟行走机器人
 *
 * @see <a href="https://leetcode-cn.com/problems/walking-robot-simulation/">LeetCode 874</a>
 */
public class WalkingRobotSimulation {

    private final int[] robotLocation = new int[]{0, 0};
    private final HashSet<String> obstacleSet = new HashSet<>();
    private int maxArea = 0;

    // 记录机器人的当前方向。0 1 2 3 分别代表"北东南西"
    private int currentDirection = 0;
    // 0 1 2 3 下标对应的方向是"北东南西"
    private final int[][] directions =
        new int[][]{new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1}, new int[]{-1, 0}};

    public int robotSim(int[] commands, int[][] obstacles) {
        // 将障碍物放入 set 中
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        // 执行命令
        for (int command : commands) {
            if (command > 0) {
                int[] direction = directions[currentDirection];

                int xLocation = robotLocation[0];
                int yLocation = robotLocation[1];
                for (int i = 1; i <= command; i++) {
                    // 获取机器人向前一步的坐标 key
                    if (obstacleSet.contains((xLocation + direction[0]) + "," + (yLocation + direction[1]))) {
                        // 遇到障碍物，停止
                        break;
                    } else {
                        // 往前走一步
                        xLocation += direction[0];
                        yLocation += direction[1];
                    }
                }

                // 将机器人向前移动
                robotLocation[0] = xLocation;
                robotLocation[1] = yLocation;
                maxArea = Math.max(maxArea, (Math.abs(xLocation * xLocation) + Math.abs(yLocation * yLocation)));
            } else {
                // 重定向：-1 向右转，-2 向左转
                if (command == -1) {
                    // 右转 90 度
                    currentDirection = (currentDirection + 1) % 4;
                } else {
                    // 左转 90 度
                    currentDirection = (currentDirection + 3) % 4;
                }
            }
        }
        return maxArea;
    }

}
