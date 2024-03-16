package array;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/maximum-number-of-balls-in-a-box/">1742. 盒子中小球的最大数量</a>
 * 你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，即 n == highLimit - lowLimit + 1）。另有无限数量的盒子，编号从 1 到 infinity 。
 * <p>
 * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
 * <p>
 * 给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：lowLimit = 1, highLimit = 10
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
 * 小球数量：2 1 1 1 1 1 1 1 1 0  0  ...
 * 编号 1 的盒子放有最多小球，小球数量为 2 。
 * 示例 2：
 * <p>
 * 输入：lowLimit = 5, highLimit = 15
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
 * 小球数量：1 1 1 1 2 2 1 1 1 0  0  ...
 * 编号 5 和 6 的盒子放有最多小球，每个盒子中的小球数量都是 2 。
 * 示例 3：
 * <p>
 * 输入：lowLimit = 19, highLimit = 28
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 12 ...
 * 小球数量：0 1 1 1 1 1 1 1 1 2  0  0  ...
 * 编号 10 的盒子放有最多小球，小球数量为 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= lowLimit <= highLimit <= 100000
 *
 * @author zhou.xu
 * @since 2022/11/23 23:15
 */
public class MaximumNumberOfBallsInABoxTest {

    public int countBalls(int lowLimit, int highLimit) {
        int[] boxes = new int[45];
        int max = 0;
        for (int i = lowLimit; i <= highLimit; i++) {
            int s = getSum(i);
            boxes[s]++;
            max = Math.max(boxes[s], max);
        }
        return max;
    }

    private int getSum(int i) {
        int s = 0;
        int t = i;
        while (t > 0) {
            s += t % 10;
            t /= 10;
        }
        return s;
    }

    @Test
    public void test() {
        System.out.println(countBalls(1, 10));
        System.out.println(countBalls(5, 15));
        System.out.println(countBalls(19, 28));
    }

    @Test
    public void test2() {
        System.out.println(getSum(19));
        System.out.println(getSum(28));
    }

    public int countBalls2(int lowLimit, int highLimit) {
        // leetcode 0ms答案
        short[] counts = new short[46];
        countBalls(counts, lowLimit - 1);
        for (int i = 1; i < counts.length; i++) {
            counts[i] *= -1;
        }
        countBalls(counts, highLimit);
        short max = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > max) {
                max = counts[i];
            }
        }
        return max;
    }

    private static void countBalls(short[] counts, int n) {
        if (n <= 0) {
            return;
        }

        if (n == 100000) {
            counts[1] += 1;
            n = 99999;
        } else {
            n += 1;
        }
        for (byte i = (byte) (n / 10000 - 1); i >= 0; i--) {
            counts[i] += 1;
            counts[i + 1] += 4;
            counts[i + 2] += 10;
            counts[i + 3] += 20;
            counts[i + 4] += 35;
            counts[i + 5] += 56;
            counts[i + 6] += 84;
            counts[i + 7] += 120;
            counts[i + 8] += 165;
            counts[i + 9] += 220;
            counts[i + 10] += 282;
            counts[i + 11] += 348;
            counts[i + 12] += 415;
            counts[i + 13] += 480;
            counts[i + 14] += 540;
            counts[i + 15] += 592;
            counts[i + 16] += 633;
            counts[i + 17] += 660;
            counts[i + 18] += 670;
            counts[i + 19] += 660;
            counts[i + 20] += 633;
            counts[i + 21] += 592;
            counts[i + 22] += 540;
            counts[i + 23] += 480;
            counts[i + 24] += 415;
            counts[i + 25] += 348;
            counts[i + 26] += 282;
            counts[i + 27] += 220;
            counts[i + 28] += 165;
            counts[i + 29] += 120;
            counts[i + 30] += 84;
            counts[i + 31] += 56;
            counts[i + 32] += 35;
            counts[i + 33] += 20;
            counts[i + 34] += 10;
            counts[i + 35] += 4;
            counts[i + 36] += 1;
        }
        if (n % 10000 == 0) {
            return;
        }

        byte pfx = (byte) ((n - 1) / 10000);
        for (byte i = (byte) (n / 1000 % 10 - 1 + pfx); i >= pfx; i--) {
            counts[i] += 1;
            counts[i + 1] += 3;
            counts[i + 2] += 6;
            counts[i + 3] += 10;
            counts[i + 4] += 15;
            counts[i + 5] += 21;
            counts[i + 6] += 28;
            counts[i + 7] += 36;
            counts[i + 8] += 45;
            counts[i + 9] += 55;
            counts[i + 10] += 63;
            counts[i + 11] += 69;
            counts[i + 12] += 73;
            counts[i + 13] += 75;
            counts[i + 14] += 75;
            counts[i + 15] += 73;
            counts[i + 16] += 69;
            counts[i + 17] += 63;
            counts[i + 18] += 55;
            counts[i + 19] += 45;
            counts[i + 20] += 36;
            counts[i + 21] += 28;
            counts[i + 22] += 21;
            counts[i + 23] += 15;
            counts[i + 24] += 10;
            counts[i + 25] += 6;
            counts[i + 26] += 3;
            counts[i + 27] += 1;
        }
        if (n % 1000 == 0) {
            return;
        }

        pfx += (byte) ((n - 1) / 1000 % 10);
        for (byte i = (byte) (n / 100 % 10 - 1 + pfx); i >= pfx; i--) {
            counts[i] += 1;
            counts[i + 1] += 2;
            counts[i + 2] += 3;
            counts[i + 3] += 4;
            counts[i + 4] += 5;
            counts[i + 5] += 6;
            counts[i + 6] += 7;
            counts[i + 7] += 8;
            counts[i + 8] += 9;
            counts[i + 9] += 10;
            counts[i + 10] += 9;
            counts[i + 11] += 8;
            counts[i + 12] += 7;
            counts[i + 13] += 6;
            counts[i + 14] += 5;
            counts[i + 15] += 4;
            counts[i + 16] += 3;
            counts[i + 17] += 2;
            counts[i + 18] += 1;
        }
        if (n % 100 == 0) {
            return;
        }

        pfx += (byte) ((n - 1) / 100 % 10);
        for (byte i = (byte) (n / 10 % 10 - 1 + pfx); i >= pfx; i--) {
            for (int j = 0; j < 10; j++) {
                counts[i + j]++;
            }
        }
        if (n % 10 == 0) {
            return;
        }

        pfx += (byte) ((n - 1) / 10 % 10);
        for (byte i = (byte) ((n - 1) % 10 + pfx); i >= pfx; i--) {
            counts[i]++;
        }
    }
}
