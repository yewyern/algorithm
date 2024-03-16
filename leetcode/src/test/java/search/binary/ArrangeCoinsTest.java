package search.binary;

/**
 * <a href="https://leetcode.cn/problems/arranging-coins">441. 排列硬币</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 19:55
 */
public class ArrangeCoinsTest {
    public int arrangeCoins(int n) {
        if (n == 1) {
            return 1;
        }
        long l = 1, r = n - 1;
        while (l <= r) {
            long m = (l + r) >> 1;
            long s = ((m + 1) * m) >> 1;
            if (s == n) {
                return (int) m;
            } else if (s < n) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return (int) (l - 1);
    }
}
