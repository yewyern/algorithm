package dynamic_programing;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/coin-change/">322. 零钱兑换</a>
 * @author xuzhou
 * @since 2024/1/10 17:42
 */
public class CoinChangeTest {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] count = new int[amount + 1];
        count[amount] = -1;
        for (int coin : coins) {
            if (coin > amount) {
                continue;
            }
            count[coin] = 1;
        }
        for (int i = 1; i < amount; i++) {
            if (count[i] > 0) {
                for (int coin : coins) {
                    int next = i + coin;
                    if (next > 0 && next <= amount) {
                        count[next] = count[next] > 0 ? Math.min(count[next], count[i] + 1) : count[i] + 1;
                    }
                }
            }
        }
        return count[amount];
    }

    public int coinChange2(int[] coins, int amount) {
        int[] cache = new int[amount + 1];
        Arrays.fill(cache, -2);
        return process(coins, amount, cache);
    }

    private int process(int[] coins, int amount, int[] cache) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (cache[amount] != -2) {
            return cache[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = process(coins, amount - coin, cache);
            if (res >= 0) {
                min = Math.min(min, res + 1);
            }
        }
        cache[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return cache[amount];
    }
}
