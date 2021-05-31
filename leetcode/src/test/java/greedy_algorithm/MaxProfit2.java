package greedy_algorithm;

import org.junit.Test;
import utils.ArrayUtils;

/**
 * <p>给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * <p>设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * <p>注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: [7,1,5,3,6,4]
 * <p>输出: 7
 * <p>解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * <p>     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * <p>示例 2:
 * <p>
 * <p>输入: [1,2,3,4,5]
 * <p>输出: 4
 * <p>解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * <p>     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * <p>     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>示例 3:
 * <p>
 * <p>输入: [7,6,4,3,1]
 * <p>输出: 0
 * <p>解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * <p>提示：
 * <p>
 * <p>1 <= prices.length <= 3 * 10 ^ 4
 * <p>0 <= prices[i] <= 10 ^ 4
 *
 * @author xzer
 */
public class MaxProfit2 {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = prices.length - 1; i > 0; i--) {
            if (prices[i] > prices[i - 1]) {
                maxProfit = maxProfit + prices[i] - prices[i - 1];
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
        test(1, 2, 3, 4, 5);
        test(7, 1, 5, 3, 6, 4);
        test(7, 6, 4, 3, 1);
    }
}