package search.binary;

/**
 * <a href="https://leetcode.cn/problems/valid-perfect-square">367. 有效的完全平方数</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 18:28
 */
public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        long r = 1, s = 1;
        while (s < num) {
            r <<= 1;
            s = r * r;
        }
        if (s == num) {
            return true;
        }
        long l = r >> 1;
        while (l <= r) {
            long m = (l + r) >> 1;
            s = m * m;
            if (s == num) {
                return true;
            } else if (s > num) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }
}
