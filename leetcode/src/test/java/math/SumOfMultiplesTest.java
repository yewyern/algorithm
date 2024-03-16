package math;

/**
 * <a href="https://leetcode.cn/problems/sum-multiples">2652. 倍数求和</a>
 * @author xuzhou
 * @since 2023/10/20 18:23
 */
public class SumOfMultiplesTest {

    public int sumOfMultiples(int n) {
        return sum(n, 3) + sum(n, 5) + sum(n, 7) - sum(n, 15) - sum(n, 21) - sum(n, 35) + sum(n, 105);
    }

    private int sum(int n, int divide) {
        int c = n / divide;
        return c * (c + 1) * divide >> 1;
    }
}
