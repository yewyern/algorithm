package greedy_algorithm;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii">123. 买卖股票的最佳时机 III</a>
 * @author xuzhou
 * @since 2024/1/18 17:24
 */
public class MaxProfit3 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, sellOnceMax(prices, 0, i) + sellOnceMax(prices, i, n));
        }
        return max;
    }

    private int sellOnceMax(int[] prices, int l, int r) {
        // todo
        if (r <= l + 1) {
            return 0;
        }
        int ans = 0;
        int i = l, j = l + 1;
        while (j < r) {
            while (j < r && prices[j] >= prices[j - 1]) {
                j++;
            }
            if (j <= r) {
                ans = Math.max(prices[j - 1] - prices[i], ans);
            }
            i = j++;
        }
        return ans;
    }
}
