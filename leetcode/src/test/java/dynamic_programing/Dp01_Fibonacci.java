package dynamic_programing;

import org.junit.Test;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *
 *
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：<a>https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof</a>
 *
 * @author xuzhou
 * @since 2021/6/7 16:40
 */
public class Dp01_Fibonacci {

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        // 动态规划
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            long temp = dp[i - 1] + dp[i - 2];
            dp[i] = temp % 1000000007;
        }
        return (int) dp[n];
    }

    public int fib2(int n) {
        if (n == 0) {
            return 0;
        }
        // 动态规划
        long pre = 0;
        long cur = 1;
        for (int i = 2; i < n + 1; i++) {
            long temp = cur + pre;
            pre = cur;
            cur = temp % 1000000007;
        }
        return (int) cur;
    }

    public int fibIt(int n) {
        // 递归,没有保存中间状态，重复计算
        // 时间复杂度：O(2^n)
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return (int) (((long) fib(n - 1) + fib(n - 2)) % 1000000007);
    }

    @Test
    public void test() {
        for (int i = 0; i < 101; i++) {
            int fib = fib(i);
            int fib2 = fib2(i);
            if (fib != fib2) {
                System.out.println("i = " + i + ", res = " + fib);
                System.out.println("i = " + i + ", res = " + fib2);
            }
        }
    }
}
