package greedy_algorithm;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii">123. 买卖股票的最佳时机 III</a>
 * @author xuzhou
 * @since 2024/1/18 17:24
 */
public class MaxProfit3 {

    @Test
    public void test() {
        System.out.println(maxProfit(new int[] {3,3,5,0,0,3,1,4}));
    }

    public int maxProfit(int[] prices) {
        // 动态规划
        int n = prices.length;
        int[] dp = new int[n];
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - min);
        }
        int ans = dp[n - 1];
        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            ans = Math.max(ans, max - prices[i] + dp[i]);
        }
        return ans;
    }

}
