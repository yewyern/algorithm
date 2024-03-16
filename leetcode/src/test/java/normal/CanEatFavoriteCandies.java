package normal;

import java.util.Arrays;
import org.junit.Test;

/**
 * <p>给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
 * <p>
 * <p>你按照如下规则进行一场游戏：
 * <p>
 * <p>你从第 0 天开始吃糖果。
 * <p>你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
 * <p>在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
 * <p>请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；否则 answer[i] 为 false 。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
 * <p>
 * <p>请你返回得到的数组 answer 。
 * <p>
 * <p>示例 1：
 * <p>
 * <p>输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
 * <p>输出：[true,false,true]
 * <p>提示：
 * <p>1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
 * <p>2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
 * <p>3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
 * <p>示例 2：
 * <p>
 * <p>输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
 * <p>输出：[false,true,true,false,false]
 */
public class CanEatFavoriteCandies {

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        long[] preSum = new long[candiesCount.length];
        for (int i = 1; i < candiesCount.length; i++) {
            preSum[i] = preSum[i - 1] + candiesCount[i];
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            // TODO
            int type = queries[i][0];
            long day = queries[i][1];
            long cap = queries[i][2];
            ans[i] = preSum[type] + candiesCount[type] > day
                && preSum[type] < (day + 1) * cap;
        }

        return ans;
    }

    public boolean[] canEatComparison(int[] candiesCount, int[][] queries) {
        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = canEat(candiesCount, queries[i]);
        }

        return ans;
    }

    /**
     * 每天吃1个，和每天都吃完的交集能覆盖favoriteDay即可
     *
     * @param candiesCount candiesCount[i] 表示你拥有的第 i 类糖果的数目
     * @param queries      [favoriteTypei, favoriteDayi, dailyCapi]
     * @return
     */
    public boolean canEat(int[] candiesCount, int[] queries) {
        int type = queries[0];
        long day = queries[1];
        long cap = queries[2];
        long preSum = 0;
        // 没有保存中间状态
        for (int i = 0; i < queries[0]; i++) {
            preSum += candiesCount[i];
        }
        return preSum + candiesCount[type] > day
            && preSum < (day + 1) * cap;
    }

    @Test
    public void test() {
        int[] candiesCount = {7, 4, 5, 3, 8};
        int[][] queries = {{0, 2, 2}, {4, 2, 4}, {2, 13, 1000000000}};
        boolean[] canEat = canEat(candiesCount, queries);
        System.out.println("canEat = " + Arrays.toString(canEat));

        int[] candiesCount2 = {5, 2, 6, 4, 1};
        int[][] queries2 = {{3, 1, 2}, {4, 10, 3}, {3, 10, 100}, {4, 100, 30}, {1, 3, 1}};
        boolean[] canEat2 = canEat(candiesCount2, queries2);
        System.out.println("canEat2 = " + Arrays.toString(canEat2));
    }

    @Test
    public void test2() {
        int[] candiesCount = {46, 5, 47, 48, 43, 34, 15, 26, 11, 25, 41, 47, 15, 25, 16, 50, 32, 42,
            32, 21, 36, 34, 50, 45, 46, 15, 46, 38, 50, 12, 3, 26, 26, 16, 23, 1, 4, 48, 47, 32, 47,
            16, 33, 23, 38, 2, 19, 50, 6, 19, 29, 3, 27, 12, 6, 22, 33, 28, 7, 10, 12, 8, 13, 24,
            21, 38, 43, 26, 35, 18, 34, 3, 14, 48, 50, 34, 38, 4, 50, 26, 5, 35, 11, 2, 35, 9, 11,
            31, 36, 20, 21, 37, 18, 34, 34, 10, 21, 8, 5};
        int[][] queries = {{80, 2329, 69}, {14, 1485, 76}, {33, 2057, 83}, {13, 1972, 27},
            {11, 387, 25}, {24, 1460, 47}, {22, 1783, 35}, {1, 513, 33}, {66, 2124, 85},
            {19, 642, 26}, {15, 1963, 79}, {93, 722, 96}, {15, 376, 88}, {60, 1864, 89},
            {86, 608, 4}, {98, 257, 35}, {35, 651, 47}, {96, 795, 73}, {62, 2077, 18},
            {27, 1724, 57}, {34, 1984, 75}, {49, 2413, 95}, {76, 1664, 5}, {28, 38, 13},
            {85, 54, 42}, {12, 301, 3}, {62, 2016, 29}, {45, 2316, 37}, {43, 2360, 28},
            {87, 192, 98}, {27, 2082, 21}, {74, 762, 37}, {51, 35, 17}, {73, 2193, 4},
            {60, 425, 65}, {11, 1522, 58}, {21, 1699, 66}, {42, 1473, 5}, {30, 2010, 48},
            {91, 796, 74}, {82, 2162, 31}, {23, 2569, 65}, {24, 684, 23}, {70, 1219, 51},
            {5, 1817, 15}, {81, 2446, 34}, {96, 771, 60}, {49, 1171, 60}, {41, 567, 67},
            {39, 799, 59}, {90, 957, 81}, {84, 2122, 27}, {82, 1707, 44}, {11, 1889, 20},
            {80, 1697, 83}, {24, 1786, 60}, {90, 1847, 99}, {51, 114, 21}, {44, 466, 85},
            {56, 469, 20}, {44, 350, 96}, {66, 1946, 10}, {14, 2470, 12}, {69, 1175, 18},
            {98, 1804, 25}, {77, 2187, 40}, {89, 2265, 45}, {19, 2246, 45}, {40, 2373, 79},
            {60, 2222, 17}, {37, 385, 5}, {97, 1759, 97}, {10, 903, 5}, {87, 842, 45},
            {74, 2398, 66}, {62, 49, 94}, {48, 156, 77}, {76, 2310, 80}, {64, 2360, 95},
            {70, 1699, 83}, {39, 1241, 66}, {92, 2312, 21}, {63, 2148, 29}, {95, 594, 74},
            {89, 90, 51}, {82, 137, 70}, {54, 301, 97}, {15, 819, 43}, {47, 1402, 60},
            {17, 2377, 43}, {50, 1937, 95}, {62, 1174, 74}, {67, 1411, 87}, {39, 1151, 48}};
//        boolean[] canEat = canEat(candiesCount, queries);
//        System.out.println("canEat = " + Arrays.toString(canEat));
        System.out.println("queries[20] = " + Arrays.toString(queries[20]));
        boolean b = canEat(candiesCount, queries[20]);
        System.out.println("b = " + b);
        // [false,false,false,false,true,false,false,false,false,false,false,true,true,false,true,true,true,true,false,false,false,false,true,false,true,true,false,false,false,true,false,true,false,false,true,false,false,false,false,true,true,false,true,true,false,false,true,true,true,true,true,true,true,false,true,false,true,true,true,true,true,false,false,true,true,false,true,false,false,false,true,true,false,true,false,true,true,false,false,true,false,true,false,true,true,true,true,false,true,false,false,true,true,true]
    }
}
