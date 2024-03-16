package dynamic_programing;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

/**
 * 背包问题
 *
 * @author zhou.xu
 * @since 2023/11/20 21:59
 */
public class KnapsackTest {

    /**
     * @param weight 每个物品的重量(可以为0)
     * @param value  每个物品的价值
     * @param bag    背包容量
     * @return 背包可以装下的最大价值
     */
    public int getMaxValue(int[] weight, int[] value, int bag) {
        int n = weight.length;
        int[][] dp = new int[n + 1][bag + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[i + 1][rest]; // 不拿有多少
                if (rest >= weight[i]) {
                    // 拿了有多少
                    int p2 = value[i] + dp[i + 1][rest - weight[i]];
                    dp[i][rest] = Math.max(p1, p2);
                } else {
                    dp[i][rest] = dp[i + 1][rest];
                }
            }
        }
        return dp[0][bag];
    }

    public int getMaxValue2(int[] weight, int[] value, int bag) {
        return process(weight, value, bag, 0);
    }

    private int process(int[] weight, int[] value, /*剩余容量*/int rest, /*当前考虑第几个物品*/int i) {
        if (i == weight.length) {
            return 0;
        }
        int p1 = process(weight, value, rest, i + 1); // 不拿有多少
        if (rest >= weight[i]) {
            // 拿了有多少
            int p2 = value[i] + process(weight, value, rest - weight[i], i + 1);
            return Math.max(p1, p2);
        }
        return p1;
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int n = RandomUtils.nextInt(1, 20);
            int[] weight = RandomArray.generate(n, 50);
            int[] value = RandomArray.generate(n, 100);
            int bag = RandomUtils.nextInt(1, 100);
            assert getMaxValue(weight, value, bag) == getMaxValue2(weight, value, bag);
        }
    }
}
