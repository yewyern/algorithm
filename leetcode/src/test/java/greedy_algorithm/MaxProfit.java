package greedy_algorithm;

import org.junit.Test;
import utils.ArrayUtils;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock">121. 买卖股票的最佳时机</a>
 * <p>给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * <p>如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * <p>注意：你不能在买入股票前卖出股票。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: [7,1,5,3,6,4]
 * <p>输出: 5
 * <p>解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * <p>     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>示例 2:
 * <p>
 * <p>输入: [7,6,4,3,1]
 * <p>输出: 0
 * <p>解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class MaxProfit {

    /**
     * 暴力解法：从前往后每次计算,O(n*n)
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int maxProfit = prices[1] - prices[0];
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int sum = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, sum);
            }
        }
        return Math.max(maxProfit, 0);
    }

    /**
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }

    private void test(int... prices) {
        System.out.println("prices = " + ArrayUtils.toString(prices));
        System.out.println("maxProfit(prices) = " + maxProfit(prices));
    }

    @Test
    public void test() {
        test(7, 1, 5, 3, 6, 4);
        test(7, 6, 4, 3, 1);
    }
}