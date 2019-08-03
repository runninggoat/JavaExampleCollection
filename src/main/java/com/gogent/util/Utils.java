package com.gogent.util;

public class Utils {

    /**
     * 求from + (from + 1) +  ... + to的和
     * @param from
     * @param to
     * @return
     */
    public static long getSum(int from, int to) {
        if (from > to) {
            throw new RuntimeException("输入的开始数大于结束数，请重新输入");
        }
        long result = 0;
        int i = from;
        do {
            result += i;
            i++;
        } while(i <= to);
        return result;
    }

    /**
     * 对一个范围，根据threadNum进行分组
     * @param from
     * @param to
     * @param threadNum
     * @return
     */
    public static int[][] getGroups(int from, int to, short threadNum) {
        int step = (to - from) / threadNum;
        int[][] groups;
        groups = new int[threadNum][2];
        for (int i = 0; i < threadNum - 1; i++) {
            groups[i][0] = from + i * step;
            groups[i][1] = from + (i + 1) * step - 1;
        }
        groups[threadNum - 1][0] = from + (threadNum - 1) * step;
        groups[threadNum - 1][1] = to;
        return groups;
    }

}
