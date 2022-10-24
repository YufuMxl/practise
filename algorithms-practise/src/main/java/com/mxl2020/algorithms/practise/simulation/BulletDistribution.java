package com.mxl2020.algorithms.practise.simulation;

public class BulletDistribution {

    /**
     * @param scores 值为士兵评分，下标为士兵编号
     * @return 每个士兵至少分一个子弹；相邻两个士兵，评分高的士兵要多分子弹。返回需要的最少子弹数
     */
    public int distributeBullet(int[] scores) {
        int count = scores.length;

        // 如果相邻两个士兵评分不一样，子弹总数 + 1
        for (int i = 1; i < scores.length; i++) {
            if (scores[i - 1] != scores[i]) count++;
        }

        // 由于重复计算了"评分中间高，两边低"的情况，所以这种情况子弹总数 - 1
        for (int i = 1; i < scores.length - 1; i++) {
            if (scores[i] > scores[i - 1] && scores[i] > scores[i + 1]) {
                count--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // 输出 5、4、7
        int[] count1 = new int[]{5, 2, 7};
        System.out.println(new BulletDistribution().distributeBullet(count1) + "");
        int[] count2 = new int[]{1, 5, 5};
        System.out.println(new BulletDistribution().distributeBullet(count2) + "");
        int[] count3 = new int[]{1, 3, 2, 2, 1};
        System.out.println(new BulletDistribution().distributeBullet(count3) + "");
    }
}
