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
        // 动态规划
        int[] count = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin) {
                    min = Math.min(min, count[i - coin]);
                }
            }
            count[i] = min == Integer.MAX_VALUE ? min : min + 1;
        }
        return count[amount] == Integer.MAX_VALUE ? -1 : count[amount];
    }

    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        // 广度优先搜索
        boolean[] visited = new boolean[amount];
        visited[0] = true;
        int[] queue = new int[amount];
        queue[0] = 0;
        int size = 1;
        int n = 1;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (i == n) {
                count++;
                n = size;
            }
            for (int coin : coins) {
                int next = queue[i] + coin;
                if (next == amount) {
                    return count;
                }
                if (next < amount && !visited[next]) {
                    visited[next] = true;
                    queue[size++] = next;
                }
            }
        }
        return -1;
    }

    public int coinChange2(int[] coins, int amount) {
        // 记忆化搜索
        int[] cache = new int[amount + 1];
        Arrays.fill(cache, Integer.MIN_VALUE);
        int res = process(coins, amount, cache);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int process(int[] coins, int amount, int[] cache) {
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return 0;
        }
        if (cache[amount] != Integer.MIN_VALUE) {
            return cache[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = process(coins, amount - coin, cache);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, res + 1);
            }
        }
        cache[amount] = min;
        return min;
    }
}
